package com.oorja.oorjaTest.repository;

import com.oorja.oorjaTest.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OfferDao extends JpaRepository<Offer, Integer> {

    public boolean existsByOfferCode(String offerCode);

    @Query(value = "SELECT discount_amount FROM offer WHERE offer_code = ?1", nativeQuery = true)
    public int getDiscountedAmount(String offerCode);

    @Query(value = "SELECT expiry_date FROM offer WHERE offer_code= ?1", nativeQuery = true)
    public java.sql.Timestamp getExpiryByOfferCode(String offerCode);
}
