package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@lombok.Getter
@lombok.Setter
@Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Entity
@Table(name = "ecomm_purchase_product_details")
public class EcommPurchaseProductDetail {
    @Id
    @Size(max = 60)
    @Column(name = "ID", nullable = false, length = 60)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
    private EcommProductDetail product;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    private EcommOrderDetail order;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Size(max = 45)
    @Column(name = "CURRENT_STATUS", length = 45)
    private String currentStatus;

    @Column(name = "AMOUNT")
    private Double amount;

}