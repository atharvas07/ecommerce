package com.ecomm.usermanagementsvc.domain.shared.repositories;

import com.ecomm.usermanagementsvc.domain.shared.entities.EcommUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserDetailsRepository extends JpaRepository<EcommUserDetails, String> {

    @Query("SELECT A FROM EcommUserDetails A WHERE A.email = :email")
    public EcommUserDetails getByEmail(@Param("email") String email);

    @Query("update EcommUserDetails e set e.password = :password where e.id = :id")
    @Modifying
    void updatePasswordById(String password, String id);

}
