package com.ecomm.productsvc.domain.shared.repositories;

import com.ecomm.productsvc.domain.shared.entities.EcommProductDetails;
import com.ecomm.productsvc.domain.shared.entities.ProductInfoProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EcommProductDetailsRepository extends JpaRepository<EcommProductDetails, Long> {

    @Query("SELECT p FROM EcommProductDetails p " +
           "JOIN FETCH p.category c WHERE p.isDeleted IS false ORDER BY p.id")
    List<ProductInfoProjection> findAllProductsWithCategory();

    @Query("SELECT p FROM EcommProductDetails p JOIN FETCH p.category c WHERE c.categoryName = :category AND p.isDeleted IS false ORDER BY p.id")
    List<ProductInfoProjection> findProductsByCategory(@Param("category") String category);


    ProductInfoProjection findAllProductsByIdAndIsDeletedIsFalse(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE EcommProductDetails p SET p.isDeleted = true WHERE p.id = :id")
    void updateIsDeletedById(@Param("id") Long id);

    Optional<EcommProductDetails> findByIdAndIsDeletedFalse(Long id);

    boolean existsByIdAndIsDeletedIsFalse(Long id);

    Page<ProductInfoProjection> findByTitleContaining(String searchTerm, Pageable pageable);
}
