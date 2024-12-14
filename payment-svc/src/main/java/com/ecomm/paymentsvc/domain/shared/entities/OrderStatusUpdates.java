package com.ecomm.paymentsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "ecomm_order_status_updates")
public class OrderStatusUpdates {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Size(max = 45)
    @Column(name = "STATUS", length = 45)
    private String status;

    @Size(max = 200)
    @Column(name = "STATUS_DESCRIPTION", length = 200)
    private String statusDescription;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}