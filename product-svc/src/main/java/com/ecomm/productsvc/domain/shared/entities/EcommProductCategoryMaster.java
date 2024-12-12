package com.ecomm.productsvc.domain.shared.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ecomm_product_category_master")
public class EcommProductCategoryMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CATEGORY_NAME", nullable = false)
    private String categoryName;

    @Column(name = "DESCRIPTION", columnDefinition = "longtext")
    private String description;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
