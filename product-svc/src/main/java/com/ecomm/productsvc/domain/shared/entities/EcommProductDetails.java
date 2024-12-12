package com.ecomm.productsvc.domain.shared.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "ecomm_product_details")
public class EcommProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "DESCRIPTION", columnDefinition = "longtext")
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID")
    private EcommProductCategoryMaster category;

    @Column(name = "IMAGE_URL", columnDefinition = "longtext")
    private String imageUrl;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "RATING")
    private Double rating;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Column(name = "RATING_COUNT")
    private int ratingCount;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setCategory(EcommProductCategoryMaster category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Double getRating() {
        return rating;
    }

    public EcommProductCategoryMaster getCategory() {
        return category;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

}
