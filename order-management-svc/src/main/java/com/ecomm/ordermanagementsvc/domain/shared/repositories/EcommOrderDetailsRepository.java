package com.ecomm.ordermanagementsvc.domain.shared.repositories;

import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommOrderDetail;
import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommOrderDetailProjection;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface EcommOrderDetailsRepository extends JpaRepository<EcommOrderDetail, String> {

    Optional<EcommOrderDetailProjection> findByOrderId(@Param("orderId") String orderId);

    Optional<EcommOrderDetailProjection> getByOrderIdAndUser_Id( String orderId, String userId);

    List<EcommOrderDetailProjection> getByUser_Id(@Size(max = 255) String userId);

    @Modifying
    @Transactional
    @Query("UPDATE EcommOrderDetail o SET o.paymentStatus = :paymentStatus, o.transactionId = :transactionId WHERE o.orderId = :orderId ")
    void updatePaymentDetails(@Param("orderId") String orderId, @Param("paymentStatus") String paymentStatus, @Param("transactionId") String transactionId);
}