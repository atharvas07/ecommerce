package com.ecomm.paymentsvc.domain.shared.repositories;

import com.ecomm.paymentsvc.domain.shared.entities.EcommOrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EcommOrderDetailsRepository extends JpaRepository<EcommOrderDetail, String> {


    @Modifying
    @Transactional
    @Query("UPDATE EcommOrderDetail o SET o.paymentStatus = :paymentStatus, o.transactionId = :transactionId WHERE o.orderId = :orderId ")
    void updatePaymentDetails(@Param("orderId") String orderId, @Param("paymentStatus") String paymentStatus, @Param("transactionId") String transactionId);

}
