package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@Table(name = "ecomm_order_status_updates")
public class EcommOrderStatusUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Size(max = 45)
    @Column(name = "STATUS", length = 45)
    private String status;

    @Size(max = 200)
    @Column(name = "STATUS_DESCRIPTION", length = 200)
    private String statusDescription;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

}