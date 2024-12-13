package com.ecomm.ordermanagementsvc.domain.shared.repositories;

import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommOrderDetail;
import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommOrderDetailProjection;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EcommOrderDetailsRepository extends JpaRepository<EcommOrderDetail, String> {

    Optional<EcommOrderDetailProjection> findByOrderId(@Param("orderId") String orderId);

    Optional<EcommOrderDetailProjection> getByOrderIdAndUser_Id( String orderId, String userId);

    List<EcommOrderDetailProjection> getByUser_Id(@Size(max = 255) String userId);
}