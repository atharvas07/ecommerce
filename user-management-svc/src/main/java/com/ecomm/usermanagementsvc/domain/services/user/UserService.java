package com.ecomm.usermanagementsvc.domain.services.user;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.usermanagementsvc.domain.dtos.request.RegisterUserClientRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<BaseResponse> registerUser(RegisterUserClientRequest request);

    ResponseEntity<BaseResponse> createSession();
}
