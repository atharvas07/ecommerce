package com.ecomm.paymentsvc.domain.shared.repositories;

import com.ecomm.paymentsvc.domain.shared.entities.EcommOrderDetails;
import com.ecomm.paymentsvc.domain.shared.entities.EcommOrderDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EcommOrderDetailsRepository extends JpaRepository<EcommOrderDetails, String> {

    EcommOrderDetailsProjection findByOrderId(@Param("orderId") String orderId);
}