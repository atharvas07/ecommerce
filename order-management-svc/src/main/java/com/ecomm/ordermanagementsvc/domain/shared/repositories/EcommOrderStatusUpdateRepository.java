package com.ecomm.ordermanagementsvc.domain.shared.repositories;

import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommOrderStatusUpdate;
import com.ecomm.ordermanagementsvc.domain.shared.entities.OrderStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EcommOrderStatusUpdateRepository extends JpaRepository<EcommOrderStatusUpdate, Integer> {

    EcommOrderStatusUpdate getFirstByOrderIdOrderByCreatedAtDesc(String orderId);

    List<OrderStatusProjection> findAllByOrderIdOrderByCreatedAtDesc(String orderId);
}