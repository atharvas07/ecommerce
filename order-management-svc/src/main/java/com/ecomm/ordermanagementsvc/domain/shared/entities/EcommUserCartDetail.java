package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name = "ecomm_user_cart_details")
public class EcommUserCartDetail {
    @Id
    @Size(max = 60)
    @Column(name = "ID", nullable = false, length = 60)
    private String id = UUID.randomUUID().toString();

    @NotNull
    @Size(max = 60)
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "UPDATED_AT")
    private Instant updatedAt;

}