package com.oorja.oorjaTest.service;

import com.oorja.oorjaTest.model.Orders;

import java.util.Optional;

public interface OrderService {

    public void createOrder(Orders order);
    public Orders getOrderById(int id);
    public void updateOrderStatus(int oid);
    public Long existsByUserIdAndOfferCode(int uid, String offerCode);

}
