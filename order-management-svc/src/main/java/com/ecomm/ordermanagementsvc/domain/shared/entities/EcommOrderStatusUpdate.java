package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "ecomm_order_status_updates")
public class EcommOrderStatusUpdate {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private EcommOrderDetail order;

    @Size(max = 45)
    @Column(name = "STATUS", length = 45)
    private String status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CREATED_AT")
    private Instant createdAt;

}