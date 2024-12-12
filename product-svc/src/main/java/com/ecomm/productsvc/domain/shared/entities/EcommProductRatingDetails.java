package com.ecomm.productsvc.domain.shared.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "ecomm_product_rating_details")
public class EcommProductRatingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RATING", nullable = false)
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private EcommProductDetails product;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

}
