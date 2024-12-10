package com.ecomm.mircrosvclib.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse {

    @JsonProperty("status")
    String status;

    @JsonProperty("statusCode")
    int statusCode;

    @JsonProperty("message")
    String message;

    @JsonProperty("respMsg")
    Object respMsg;


    public int getStatusCode() {
        return statusCode;
    }

    public Object getRespMsg() {
        return respMsg;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRespMsg(Object respMsg) {
        this.respMsg = respMsg;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public static BaseResponse getSuccessResponse(Object respMsg) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setRespMsg(respMsg);
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("OK");
        baseResponse.setStatus("success");
        return baseResponse;
    }

    public static BaseResponse getErrorResponse(String errorMessage) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("error");
        baseResponse.setMessage(errorMessage);
        baseResponse.setStatusCode(500);
        return baseResponse;
    }

    public static BaseResponse getClientErrorResponse(String errorMessage) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(errorMessage);
        baseResponse.setStatusCode(400);
        baseResponse.setStatus("error");
        return baseResponse;
    }

    public ResponseEntity<BaseResponse> toResponseEntity() {
        HttpStatus httpStatus = HttpStatus.valueOf(this.statusCode);
        return new ResponseEntity<>(this, httpStatus);
    }
}

