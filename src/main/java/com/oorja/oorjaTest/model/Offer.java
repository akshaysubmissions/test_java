package com.oorja.oorjaTest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ofid;

    private String offerCode;
    private double discountAmount;
    private Date expiryDate;

    public int getOfid() {
        return ofid;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "ofid=" + ofid +
                ", offerCode='" + offerCode + '\'' +
                ", discountAmount=" + discountAmount +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
