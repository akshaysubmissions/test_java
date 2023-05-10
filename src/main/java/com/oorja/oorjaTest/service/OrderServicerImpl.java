package com.oorja.oorjaTest.service;

import com.oorja.oorjaTest.customException.EmptyInputException;
import com.oorja.oorjaTest.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import com.oorja.oorjaTest.repository.OrderDao;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;

@Service
public class OrderServicerImpl implements OrderService{

    @Autowired
    OrderDao orderDao;
    @Override
    public void createOrder(Orders order) {
        orderDao.save(order);
    }

    @Override
    public Orders getOrderById(int id) {
        try{

            if (id <= 0){
                throw new EmptyInputException("901", "Order ID should be greater than 0");
            }
            Optional<Orders> order = orderDao.findById(id);
            return order.orElse(null);

        }catch (MethodArgumentTypeMismatchException e){
            throw new EmptyInputException("902", "Order ID should be Integer" + e.getMessage());

        }catch (Exception e){
            throw new EmptyInputException("903", "Something went wrong while retrieving order by ID" + e.getMessage());
        }

    }

    @Override
    public void updateOrderStatus(int oid) {

    }

    @Override
    public Long existsByUserIdAndOfferCode(int uid, String offerCode) {
       return orderDao.existsByuidAndofferCode(uid, offerCode);
    }
}
