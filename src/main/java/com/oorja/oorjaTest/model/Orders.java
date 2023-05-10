package com.oorja.oorjaTest.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;

    @ManyToOne
    @JoinColumn(name="uid")
    private RegisteredUsers user;

    @ManyToOne
    @JoinColumn(name="pid")
    private Products products;
    private int quatity;
    private double OrderPrice;
    private String orderStatus;

    private String offerCode;

    private Date orderDate;

    public Orders() {
        Date date = new Date();
        orderDate = date;
    }

    public double getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    private double discountedAmount;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public RegisteredUsers getUser() {
        return user;
    }

    public void setUser(RegisteredUsers user) {
        this.user = user;
    }

    public int getQuatity() {
        return quatity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public double getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(double OrderPrice) {
        this.OrderPrice = OrderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }
}
