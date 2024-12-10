package com.ecomm.usermanagementsvc.domain.application;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.usermanagementsvc.domain.services.redis.RedisService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/register")
    public BaseResponse registerUser(){
        return BaseResponse.getSuccessResponse("");
    }

    public BaseResponse login(){
        return BaseResponse.getSuccessResponse("User logged in successfully");
    }

    public BaseResponse logout(){
        return BaseResponse.getSuccessResponse("User logged out successfully");
    }

    public BaseResponse changePassword(){
        return BaseResponse.getSuccessResponse("User password changed successfully");
    }

    public BaseResponse editProfile(){
        return BaseResponse.getSuccessResponse("User profile updated successfully");
    }

    @GetMapping("/generateSession")
    public BaseResponse generateSession() throws JSONException {
        String sessionId = UUID.randomUUID().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", LocalDateTime.now());
        redisService.saveValueWithExpiry(sessionId, jsonObject,432000); //5 days

        JSONObject response = new JSONObject();
        response.put("sessionId", sessionId);
        response.put("timeout", 432000);
        return BaseResponse.getSuccessResponse(response.toString());
    }
}
