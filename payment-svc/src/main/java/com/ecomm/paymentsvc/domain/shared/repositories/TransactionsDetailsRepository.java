package com.ecomm.paymentsvc.domain.shared.repositories;

import com.ecomm.paymentsvc.domain.shared.entities.EcommTransactionsDetail;
import com.ecomm.paymentsvc.domain.shared.entities.TransactionDetailsProjection;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface TransactionsDetailsRepository extends JpaRepository<EcommTransactionsDetail, String> {

    EcommTransactionsDetail findByPaymentRefNumber(@Size(max = 100) @NotNull String paymentRefNumber);

    List<EcommTransactionsDetail> findByStatus(@Size(max = 45) String status);

    @Query("SELECT t.id AS id, t.paymentRefNumber AS paymentRefNumber, t.amount AS amount, " +
            "t.status AS status, t.method AS method, t.userId AS userId " +
            "FROM EcommTransactionsDetail t WHERE t.id = ?1")
    Optional<TransactionDetailsProjection> findTransactionProjectionById(String transactionId);

    TransactionDetailsProjection findByIdAndUserId(@Size(max = 60) String id, @Size(max = 60) String userId);
}
