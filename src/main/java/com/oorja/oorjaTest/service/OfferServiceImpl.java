package com.oorja.oorjaTest.service;

import com.oorja.oorjaTest.repository.OfferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    OfferDao offerDao;
    @Override
    public boolean isOfferCodeExist(String offerCode) {
       return offerDao.existsByOfferCode(offerCode);
    }

    @Override
    public int getDiscountedAmountByOfferCode(String offerCode) {
        return offerDao.getDiscountedAmount(offerCode);
    }

    @Override
    public java.sql.Timestamp getExpiryDateByOfferCode(String offerCode) {
        return offerDao.getExpiryByOfferCode(offerCode);
    }
}
