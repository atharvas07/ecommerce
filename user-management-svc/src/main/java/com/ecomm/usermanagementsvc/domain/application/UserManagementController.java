package com.ecomm.usermanagementsvc.domain.application;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.usermanagementsvc.domain.dtos.request.LoginClientRequest;
import com.ecomm.usermanagementsvc.domain.dtos.request.RegisterUserClientRequest;
import com.ecomm.usermanagementsvc.domain.dtos.request.ResetPasswordClientRequest;
import com.ecomm.usermanagementsvc.domain.services.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerUser(@Valid @RequestBody RegisterUserClientRequest request) {
        return userService.registerUser(request);
    }

    @GetMapping("/logout")
    public ResponseEntity<BaseResponse> logout(HttpServletRequest httpServletRequest) {
        String sessionId = httpServletRequest.getHeader("session-id");
        return userService.logout(sessionId);
    }

    @GetMapping("/generate-session")
    public ResponseEntity<BaseResponse> generateSession() {
        return userService.createSession();
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(HttpServletRequest servletRequest, @Valid @RequestBody LoginClientRequest request) {
        String sessionId = servletRequest.getHeader("session-id");
        return userService.login(request, sessionId);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<BaseResponse> resetPassword(HttpServletRequest servletRequest, @Valid @RequestBody ResetPasswordClientRequest request, HttpSession httpSession) {
        String sessionId = servletRequest.getHeader("session-id");
        return userService.resetPassword(request, sessionId);
    }

}