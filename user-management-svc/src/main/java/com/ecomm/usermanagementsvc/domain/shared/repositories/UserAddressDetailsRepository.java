package com.ecomm.usermanagementsvc.domain.shared.repositories;

import com.ecomm.usermanagementsvc.domain.shared.entities.EcommUserAddressDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserAddressDetailsRepository extends JpaRepository<EcommUserAddressDetails, String> {
    List<EcommUserAddressDetails> findAllByUserId(String userId);
}
