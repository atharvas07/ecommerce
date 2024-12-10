package com.ecomm.usermanagementsvc.domain.impl;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.mircrosvclib.utils.CommonUtils;
import com.ecomm.mircrosvclib.utils.JsonUtils;
import com.ecomm.usermanagementsvc.domain.dtos.request.RegisterUserClientRequest;
import com.ecomm.usermanagementsvc.domain.dtos.response.RegisterUserClientResponse;
import com.ecomm.usermanagementsvc.domain.services.redis.RedisService;
import com.ecomm.usermanagementsvc.domain.services.user.UserService;
import com.ecomm.usermanagementsvc.domain.shared.entities.EcommUserDetails;
import com.ecomm.usermanagementsvc.domain.shared.repositories.UserDetailsRepository;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final int SESSION_EXPIRY_TIME_SECONDS = 432000; // 5 days
    private static final String ERROR_USER_EXISTS = "User already exists";
    private static final String ERROR_SESSION_FAILED = "Failed to generate session";

    private final UserDetailsRepository userDetailsRepository;

    private final RedisService redisService;

    public UserServiceImpl(UserDetailsRepository userDetailsRepository, RedisService redisService) {
        this.userDetailsRepository = userDetailsRepository;
        this.redisService = redisService;
    }

    @Override
    public ResponseEntity<BaseResponse> registerUser(RegisterUserClientRequest request) {
        try {
            EcommUserDetails userDetails = mapToEcommUserDetails(request);
            EcommUserDetails savedDetails = userDetailsRepository.save(userDetails);
            RegisterUserClientResponse response = mapToRegisterUserClientResponse(savedDetails);
            return BaseResponse.getSuccessResponse(response).toResponseEntity();
        } catch (DataIntegrityViolationException e) {
            return BaseResponse.getErrorResponse(ERROR_USER_EXISTS).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> createSession() {
        try {
            String sessionId = generateSession();
            JSONObject responseJson = createSessionResponseJson(sessionId);
            return BaseResponse.getSuccessResponse(JsonUtils.getBeanByJson(responseJson.toString(), Object.class)).toResponseEntity();
        } catch (JSONException e) {
            return BaseResponse.getErrorResponse(ERROR_SESSION_FAILED).toResponseEntity();
        }
    }

    private EcommUserDetails mapToEcommUserDetails(RegisterUserClientRequest request) {
        EcommUserDetails userDetails = new EcommUserDetails();
        userDetails.setEmail(request.getEmail());
        userDetails.setPassword(CommonUtils.encodeMD5(request.getPassword()));
        userDetails.setFirstName(request.getFirstName());
        userDetails.setLastName(request.getLastName());
        userDetails.setUserName(request.getUserName());
        userDetails.setMobile(request.getMobile());
        return userDetails;
    }

    private RegisterUserClientResponse mapToRegisterUserClientResponse(EcommUserDetails savedDetails) {
        RegisterUserClientResponse response = new RegisterUserClientResponse();
        response.setUserId(savedDetails.getId());
        response.setEmail(savedDetails.getEmail());
        response.setFirstName(savedDetails.getFirstName());
        response.setLastName(savedDetails.getLastName());
        response.setUserName(savedDetails.getUserName());
        response.setMobile(savedDetails.getMobile());
        return response;
    }

    private String generateSession() throws JSONException {
        String sessionId = UUID.randomUUID().toString();
        JSONObject sessionData = new JSONObject();
        sessionData.put("time", LocalDateTime.now());
        sessionData.put("isLoggedIn", false);
        redisService.saveValueWithExpiry(sessionId, sessionData, SESSION_EXPIRY_TIME_SECONDS);
        return sessionId;
    }

    private JSONObject createSessionResponseJson(String sessionId) throws JSONException {
        JSONObject responseJson = new JSONObject();
        responseJson.put("sessionId", sessionId);
        responseJson.put("timeout", SESSION_EXPIRY_TIME_SECONDS);
        responseJson.put("data", JsonUtils.getBeanByJson(redisService.getValue(sessionId).toString(), Object.class));
        return responseJson;
    }
}