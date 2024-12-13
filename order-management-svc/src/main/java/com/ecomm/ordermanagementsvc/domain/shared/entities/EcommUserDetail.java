package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ecomm_user_details")
public class EcommUserDetail {
    @Id
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private String id;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "MOBILE")
    private Long mobile;

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @OneToMany
    private Set<EcommOrderDetail> ecommOrderDetails = new LinkedHashSet<>();

    @OneToMany
    private Set<EcommUserAddressDetail> ecommUserAddressDetails = new LinkedHashSet<>();

}