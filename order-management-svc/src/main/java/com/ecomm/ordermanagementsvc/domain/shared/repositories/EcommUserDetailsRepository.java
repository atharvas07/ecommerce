package com.ecomm.ordermanagementsvc.domain.shared.repositories;

import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommUserDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EcommUserDetailsRepository extends JpaRepository<EcommUserDetail, String> {
}
