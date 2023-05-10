package com.oorja.oorjaTest.service;


import java.util.Date;

public interface OfferService {

    public boolean isOfferCodeExist(String offerCode);

    public int getDiscountedAmountByOfferCode(String offerCode);

    public java.sql.Timestamp getExpiryDateByOfferCode(String offerCode);

}
