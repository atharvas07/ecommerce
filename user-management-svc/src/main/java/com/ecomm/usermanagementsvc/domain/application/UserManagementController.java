package com.ecomm.usermanagementsvc.domain.application;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.usermanagementsvc.domain.dtos.request.RegisterUserClientRequest;
import com.ecomm.usermanagementsvc.domain.services.redis.RedisService;
import com.ecomm.usermanagementsvc.domain.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    private final RedisService redisService;
    private final UserService userService;

    @Autowired
    public UserManagementController(RedisService redisService, UserService userService) {
        this.redisService = redisService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerUser(@Valid @RequestBody RegisterUserClientRequest request) {
        return userService.registerUser(request);
    }

    @GetMapping("/logout/{sessionId}")
    public BaseResponse logout(@PathVariable String sessionId) {
        redisService.deleteValue(sessionId); // Clear session from Redis
        return BaseResponse.getSuccessResponse("User logged out successfully");
    }

    @GetMapping("/generateSession")
    public ResponseEntity<BaseResponse> generateSession() {
        return userService.createSession();
    }

}