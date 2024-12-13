package com.ecomm.ordermanagementsvc.domain.shared.repositories;

import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommUserCartDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface EcommUserCartRepository extends JpaRepository<EcommUserCartDetail, String> {

    Optional<EcommUserCartDetail> findByUserIdAndIsActive(String userId, boolean isActive);
}
