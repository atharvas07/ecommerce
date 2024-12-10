package com.ecomm.usermanagementsvc.domain.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserClientRequest {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("mobile")
    private String mobile;

}
