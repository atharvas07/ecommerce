package com.ecomm.paymentsvc.domain.shared.repositories;

import com.ecomm.paymentsvc.domain.shared.entities.EcommTransactionsDetail;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface TransactionsDetailsRepository extends JpaRepository<EcommTransactionsDetail, String> {

    EcommTransactionsDetail findByPaymentRefNumber(@Size(max = 100) @NotNull String paymentRefNumber);

    List<EcommTransactionsDetail> findByStatus(@Size(max = 45) String status);
}
