package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "ecomm_purchase_product_details")
public class EcommPurchaseProductDetail {
    @Id
    @Size(max = 60)
    @Column(name = "ID", nullable = false, length = 60)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private EcommProductDetail product;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private EcommOrderDetail order;

    @Size(max = 45)
    @Column(name = "CURRENT_STATUS", length = 45)
    private String currentStatus;

    @Column(name = "AMOUNT")
    private Double amount;

}