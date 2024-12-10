package com.ecomm.usermanagementsvc.domain.shared.repositories;

import com.ecomm.usermanagementsvc.domain.shared.entities.EcommUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserDetailsRepository extends JpaRepository<EcommUserDetails, String> {


}
