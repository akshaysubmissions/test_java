package com.oorja.oorjaTest.repository;

import com.oorja.oorjaTest.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT EXISTS(SELECT 1 FROM Orders WHERE uid = :uid AND offer_code = :offerCode)", nativeQuery = true)
    public Long existsByuidAndofferCode(@Param(value = "uid") int uid, @Param(value = "offerCode") String offerCode);
}
