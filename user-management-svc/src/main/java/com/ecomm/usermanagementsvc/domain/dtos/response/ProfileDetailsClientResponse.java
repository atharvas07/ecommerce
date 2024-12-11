package com.ecomm.usermanagementsvc.domain.dtos.response;

import com.ecomm.usermanagementsvc.domain.dtos.request.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProfileDetailsClientResponse {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobile")
    private Long mobile;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("addressList")
    private List<Address> addressList = new ArrayList<>();

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
