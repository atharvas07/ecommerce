package com.ecomm.productsvc.domain.shared.repositories;

import com.ecomm.productsvc.domain.shared.entities.EcommProductCategoryMaster;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface EcommProductCategoriesRepository extends JpaRepository<EcommProductCategoryMaster, Integer> {

    Optional<EcommProductCategoryMaster> findByCategoryName(String categoryName);
}
