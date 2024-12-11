package com.ecomm.usermanagementsvc.domain.services.user;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.usermanagementsvc.domain.dtos.request.LoginClientRequest;
import com.ecomm.usermanagementsvc.domain.dtos.request.ModifyDetailsClientRequest;
import com.ecomm.usermanagementsvc.domain.dtos.request.RegisterUserClientRequest;
import com.ecomm.usermanagementsvc.domain.dtos.request.ResetPasswordClientRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<BaseResponse> registerUser(RegisterUserClientRequest request);

    ResponseEntity<BaseResponse> createSession();

    ResponseEntity<BaseResponse> login(LoginClientRequest request, String sessionId);

    ResponseEntity<BaseResponse> logout(String sessionId);

    ResponseEntity<BaseResponse> modifyDetails(ModifyDetailsClientRequest request, String sessionId);

    ResponseEntity<BaseResponse> resetPassword(ResetPasswordClientRequest request, String sessionId);
}
