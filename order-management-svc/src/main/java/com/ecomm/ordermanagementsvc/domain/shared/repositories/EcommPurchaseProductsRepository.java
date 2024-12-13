package com.ecomm.ordermanagementsvc.domain.shared.repositories;

import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommPurchaseProductDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EcommPurchaseProductsRepository extends JpaRepository<EcommPurchaseProductDetail, String> {
}
