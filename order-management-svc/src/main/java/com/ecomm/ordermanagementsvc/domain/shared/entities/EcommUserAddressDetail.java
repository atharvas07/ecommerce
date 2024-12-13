package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "ecomm_user_address_details")
public class EcommUserAddressDetail {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private EcommUserDetail user;

    @Size(max = 20)
    @Column(name = "MOBILE", length = 20)
    private String mobile;

    @Size(max = 150)
    @NotNull
    @Column(name = "ADDRESS_LINE_1", nullable = false, length = 150)
    private String addressLine1;

    @Size(max = 150)
    @Column(name = "ADDRESS_LINE_2", length = 150)
    private String addressLine2;

    @Size(max = 100)
    @NotNull
    @Column(name = "CITY", nullable = false, length = 100)
    private String city;

    @Size(max = 100)
    @NotNull
    @Column(name = "STATE", nullable = false, length = 100)
    private String state;

    @Size(max = 100)
    @NotNull
    @Column(name = "COUNTRY", nullable = false, length = 100)
    private String country;

    @Size(max = 20)
    @NotNull
    @Column(name = "PIN_CODE", nullable = false, length = 20)
    private String pinCode;


}