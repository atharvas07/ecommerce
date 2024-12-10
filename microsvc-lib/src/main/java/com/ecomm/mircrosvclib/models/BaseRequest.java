package com.ecomm.mircrosvclib.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest {

    @JsonProperty("userId")
    String userId;

    @JsonProperty("mobile")
    String sessionId;

}
