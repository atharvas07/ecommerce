package com.ecomm.paymentsvc.domain.shared.repositories;

import com.ecomm.paymentsvc.domain.shared.entities.OrderStatusUpdates;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EcommOrderStatusUpdatesRepository extends JpaRepository<OrderStatusUpdates, Integer> {
}
