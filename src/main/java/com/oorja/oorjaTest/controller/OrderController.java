package com.oorja.oorjaTest.controller;

import com.oorja.oorjaTest.model.Orders;
import com.oorja.oorjaTest.model.Products;
import com.oorja.oorjaTest.model.RegisteredUsers;
import com.oorja.oorjaTest.service.OfferService;
import com.oorja.oorjaTest.service.ProductService;
import com.oorja.oorjaTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oorja.oorjaTest.service.OrderService;
import com.oorja.oorjaTest.Misc.CheckWeekend;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = "*", allowCredentials = "false")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @Autowired
    OfferService offerService;

    @Value("${minimum_order_amount}")
    private int MIN_AMOUNT;

    @Value("${maximum_order_amount}")
    private int MAX_AMOUNT;

    /*
    @GetMapping("createNewOrder/{uid}/{pid}/{quatity}/{OrderPrice}/{orderStatus}/{offerCode}")
    public ResponseEntity<String> createOrder(@PathVariable int uid, @PathVariable int pid, @PathVariable int quatity, @PathVariable double OrderPrice, @PathVariable String orderStatus, @PathVariable String offerCode){


        if(!productService.findById(pid)){
            return ResponseEntity.badRequest().body("Product with ID: " + pid + " is not present in database");
        }

        //check for valid offer code
        if(!offerService.isOfferCodeExist(offerCode)){
            return ResponseEntity.badRequest().body(offerCode + " is a invalid offer code");
        };

        //check if offer code is expired or not

        java.sql.Timestamp expiry_date_offer_code = offerService.getExpiryDateByOfferCode(offerCode);

         if(isOfferCodeExpired(expiry_date_offer_code)){
             return ResponseEntity.badRequest().body(offerCode + " is expired. We are really sorry!");
         }

        //check if user has already used given offer code
        Long count = orderService.existsByUserIdAndOfferCode(uid, offerCode);

        if(count != null && count > 0){
            return ResponseEntity.badRequest().body("User with ID: " + uid + " has already claimed " + offerCode + " offer coupon");
        }

        //now user is valid for discount. let's give him discount
         double total_order_price = OrderPrice * quatity;
         int discount = offerService.getDiscountedAmountByOfferCode(offerCode);
         double total_discount = total_order_price - discount;



        //Check if today is Sunday or not
        if (isTodaySunday()) {
            return ResponseEntity.badRequest().body("Orders are not accepted on public holidays or Sundays.");
        }

        //Check for max and min amount of order
        if(OrderPrice < MIN_AMOUNT || OrderPrice > MAX_AMOUNT){
            return ResponseEntity.badRequest().body("Order amount must be greater than or equal to 99 rupees and less than or equal to 4999");
        }

        // Check if there is enough quantity of the product

        Products product = productService.findByIdForQuantity(pid);
        int currentQuantity = product.getQuantity();

        if(currentQuantity < quatity ) {
            return ResponseEntity.badRequest().body("Product is not available to order!");
        }else {
            product.setQuantity(currentQuantity - quatity);
            productService.addProduct(product);
        }


        RegisteredUsers user = new RegisteredUsers();
        user.setUid(uid);

        Products products = new Products();
        products.setPid(pid);

        Orders order = new Orders();
        order.setUser(user);
        order.setProducts(products);
        order.setQuatity(quatity);
        order.setOrderPrice(OrderPrice);
        order.setOrderStatus(orderStatus);
        order.setOrderStatus(orderStatus);
        order.setOfferCode(offerCode);
        order.setDiscountedAmount(total_discount);

        orderService.createOrder(order);

        return ResponseEntity.ok().body("Order placed successfully. Net amount after discount applied: " + order.getDiscountedAmount());
    }
    */

    @PostMapping("/api/addOrder/{uid}/{pid}")
    public ResponseEntity<String> createOrder(@PathVariable int uid, @PathVariable int pid, @RequestBody Orders orders) throws Exception {

        if(!userService.existsById(uid)){
            return ResponseEntity.badRequest().body("User with ID: " + uid + " is not present in database");
        }

        if(!productService.findById(pid)){
            return ResponseEntity.badRequest().body("Product with ID: " + pid + " is not present in database");
        }

        //check for valid offer code
        if(!offerService.isOfferCodeExist(orders.getOfferCode())){
                return ResponseEntity.badRequest().body(orders.getOfferCode() + " is a invalid offer code");
        };

        //check if offer code is expired or not
        java.sql.Timestamp expiry_date_offer_code = offerService.getExpiryDateByOfferCode(orders.getOfferCode());

        if(isOfferCodeExpired(expiry_date_offer_code)){
            return ResponseEntity.badRequest().body(orders.getOfferCode() + " is expired. We are really sorry!");
        }

        //check if user has already used given offer code
        Long count = orderService.existsByUserIdAndOfferCode(uid, orders.getOfferCode());

        if(count != null && count > 0){
            return ResponseEntity.badRequest().body("User with ID: " + uid + " has already claimed " + orders.getOfferCode() + " offer coupon");
        }

        if(orders.getQuatity() <= 0){
            return ResponseEntity.badRequest().body("Select atleast 1 quanity");
        }

        //now user is valid for discount. let's give him discount
        double total_order_price = orders.getOrderPrice() * orders.getQuatity();
        int discount = offerService.getDiscountedAmountByOfferCode(orders.getOfferCode());
        double total_discount = total_order_price - discount;



        //Check if today is Sunday or any indian public holiday
        if (CheckWeekend.isTodaySunday() || CheckWeekend.checkIndianPublicHolidayToday()) {
            return ResponseEntity.badRequest().body("Orders are not accepted on public holidays or Sundays.");
        }

        //Check for max and min amount of order
        if(orders.getOrderPrice() < MIN_AMOUNT || orders.getOrderPrice() > MAX_AMOUNT){
            return ResponseEntity.badRequest().body("Order amount must be greater than or equal to 99 rupees and less than or equal to 4999");
        }

        // Check if there is enough quantity of the product

        Products product = productService.findByIdForQuantity(pid);
        int currentQuantity = product.getQuantity();

        if(currentQuantity < orders.getQuatity() ) {
            return ResponseEntity.badRequest().body("Product is not available to order!");
        }else {
            product.setQuantity(currentQuantity - orders.getQuatity());
            productService.addProduct(product);
        }


        RegisteredUsers user = new RegisteredUsers();
        user.setUid(uid);

        Products products = new Products();
        products.setPid(pid);

        orders.setUser(user);
        orders.setProducts(products);
        orders.setDiscountedAmount(total_discount);

        orderService.createOrder(orders);

        return ResponseEntity.ok().body("Order placed successfully. Net amount after discount applied: " + orders.getDiscountedAmount());
    }


    @GetMapping("/api/getOrderById/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id){

            Orders order = orderService.getOrderById(id);
            if (order == null) {
                return ResponseEntity.badRequest().body("No order with ID: " + id);

            } else {
                return ResponseEntity.ok(order);
            }
    }

    @GetMapping("/api/updateOrderStatus/{oid}")
     public ResponseEntity<?> updateOrderStatus(@PathVariable int oid ){

        Orders order = orderService.getOrderById(oid);
        if( order == null){
            return ResponseEntity.badRequest().body("No order with ID: " + oid);
        }
        if(order.getOrderStatus().equals("confirmed")){
            order.setOrderStatus("not confirmed");
            orderService.createOrder(order);
        }else{
            order.setOrderStatus("confirmed");
            orderService.createOrder(order);
        }

        return  ResponseEntity.ok("Order status changed to: " + order.getOrderStatus());

    }


    public boolean isOfferCodeExpired(java.sql.Timestamp expiry_date_offer_code){
        Date currDate = new Date();
        Date expiryDate = new Date(expiry_date_offer_code.getTime());

        if (expiryDate.before(currDate)) {
            return true;
        }
        return false;
    }


}
