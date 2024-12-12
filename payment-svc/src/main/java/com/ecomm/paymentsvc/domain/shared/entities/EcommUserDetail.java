package com.ecomm.paymentsvc.domain.shared.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ecomm_user_details")
public class EcommUserDetail {
    @Id
    @Size(max = 60)
    @Column(name = "ID", nullable = false, length = 60)
    private String id;

    @Size(max = 100)
    @NotNull
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "MOBILE")
    private Long mobile;

    @Size(max = 50)
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getMobile() {
        return mobile;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }
}