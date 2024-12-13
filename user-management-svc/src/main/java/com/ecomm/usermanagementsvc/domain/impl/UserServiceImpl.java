package com.ecomm.usermanagementsvc.domain.impl;

import com.ecomm.mircrosvclib.exception.CustomException;
import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.mircrosvclib.utils.CommonUtils;
import com.ecomm.mircrosvclib.utils.JsonUtils;
import com.ecomm.usermanagementsvc.domain.dtos.request.*;
import com.ecomm.usermanagementsvc.domain.dtos.response.ProfileDetailsClientResponse;
import com.ecomm.usermanagementsvc.domain.dtos.response.RegisterUserClientResponse;
import com.ecomm.usermanagementsvc.domain.services.JwtService;
import com.ecomm.usermanagementsvc.domain.services.redis.RedisService;
import com.ecomm.usermanagementsvc.domain.services.user.UserService;
import com.ecomm.usermanagementsvc.domain.shared.entities.EcommUserAddressDetails;
import com.ecomm.usermanagementsvc.domain.shared.entities.EcommUserDetails;
import com.ecomm.usermanagementsvc.domain.shared.repositories.UserAddressDetailsRepository;
import com.ecomm.usermanagementsvc.domain.shared.repositories.UserDetailsRepository;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecomm.mircrosvclib.exception.ExceptionHandler.getDuplicateFieldErrorMessage;

@Service
public class UserServiceImpl implements UserService {

    private static final int SESSION_EXPIRY_TIME_SECONDS = 432000; // 5 days
    private static final String ERROR_SESSION_FAILED = "Failed to generate session";
    private static final String ERROR_USER_NOT_EXIST = "User does not exist";
    private static final String ERROR_INVALID_PASSWORD = "Invalid password";
    private static final String ERROR_INVALID_SESSION = "Invalid session";
    private static final String SUCCESS_PASSWORD_RESET = "Password reset successfully";

    private final UserDetailsRepository userDetailsRepository;
    private final UserAddressDetailsRepository userAddressDetailsRepository;
    private final RedisService redisService;
    private final JwtService jwtService;

    public UserServiceImpl(UserDetailsRepository userDetailsRepository, RedisService redisService, UserAddressDetailsRepository userAddressDetailsRepository, JwtService jwtService) {
        this.userDetailsRepository = userDetailsRepository;
        this.redisService = redisService;
        this.userAddressDetailsRepository = userAddressDetailsRepository;
        this.jwtService = jwtService;
    }

    private static EcommUserAddressDetails getEcommUserAddressDetails(Address address, String userId) {
        EcommUserAddressDetails userAddressDetails = new EcommUserAddressDetails();
        userAddressDetails.setAddressLine1(address.getAddressLine1());
        userAddressDetails.setAddressLine2(address.getAddressLine2());
        userAddressDetails.setCity(address.getCity());
        userAddressDetails.setCountry(address.getCountry());
        userAddressDetails.setState(address.getState());
        userAddressDetails.setId(address.getId());
        userAddressDetails.setPrimary(address.getPrimary());
        userAddressDetails.setPinCode(address.getPinCode());
        userAddressDetails.setMobile(address.getMobile());
        userAddressDetails.setUserId(userId);
        return userAddressDetails;
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

    @Override
    public ResponseEntity<BaseResponse> login(LoginClientRequest request, String sessionId) {
        try {
            EcommUserDetails userDetails = validateUserCredentials(request);
            String jwtToken = jwtService.generateToken(userDetails.getId(), userDetails.getEmail(), sessionId);
            updateRedisSession(sessionId, userDetails, request.getEmail(), jwtToken);
            return BaseResponse.getSuccessResponse(jwtToken).toResponseEntity();

        } catch (CustomException ce) {
            return BaseResponse.getClientErrorResponse(ce.getMessage()).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> logout(String sessionId) {
        try {
            redisService.deleteValue(sessionId);
            return BaseResponse.getSuccessResponse("Logged out successfully").toResponseEntity();
        } catch (Exception ex) {
            return BaseResponse.getErrorResponse(ex.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> registerUser(RegisterUserClientRequest request) {
        try {
            EcommUserDetails userDetails = mapToEcommUserDetails(request);
            EcommUserDetails savedDetails = userDetailsRepository.save(userDetails);
            RegisterUserClientResponse response = mapToRegisterUserClientResponse(savedDetails);
            return BaseResponse.getSuccessResponse(response).toResponseEntity();
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getDuplicateFieldErrorMessage(e);
            return BaseResponse.getErrorResponse(errorMessage).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> resetPassword(ResetPasswordClientRequest request, String sessionId) {
        try {
            EcommUserDetails userDetails = userDetailsRepository.getByEmail(request.getEmail());
            if (userDetails == null) {
                throw new CustomException(ERROR_USER_NOT_EXIST);
            }
            if (!userDetails.getPassword().equals(CommonUtils.encodeMD5(request.getPassword()))) {
                throw new CustomException(ERROR_INVALID_PASSWORD);
            }
            userDetailsRepository.updatePasswordById(CommonUtils.encodeMD5(request.getNewPassword()), userDetails.getId());
            redisService.deleteValue(sessionId);
            return BaseResponse.getSuccessResponse(SUCCESS_PASSWORD_RESET).toResponseEntity();
        } catch (CustomException ce) {
            return BaseResponse.getClientErrorResponse(ce.getMessage()).toResponseEntity();
        } catch (Exception ex) {
            return BaseResponse.getErrorResponse(ex.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> modifyDetails(ModifyDetailsClientRequest request, String sessionId) {
        RegisterUserClientResponse response = new RegisterUserClientResponse();
        try {
            Optional<EcommUserDetails> existingDetails = userDetailsRepository.findById(request.getUserId());
            if (existingDetails.isEmpty()) {
                throw new CustomException(ERROR_USER_NOT_EXIST);
            }
            existingDetails.get().setMobile(request.getMobile());
            existingDetails.get().setUserName(request.getUserName());
            existingDetails.get().setEmail(request.getEmail());
            existingDetails.get().setFirstName(request.getFirstName());
            existingDetails.get().setLastName(request.getLastName());
            EcommUserDetails updatedDetails = userDetailsRepository.save(existingDetails.get());

            response.setFirstName(updatedDetails.getFirstName());
            response.setLastName(updatedDetails.getLastName());
            response.setUserId(updatedDetails.getId());
            response.setMobile(updatedDetails.getMobile());
            response.setUserName(updatedDetails.getUserName());
            response.setEmail(updatedDetails.getEmail());

            return BaseResponse.getSuccessResponse(response).toResponseEntity();
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getDuplicateFieldErrorMessage(e);
            return BaseResponse.getErrorResponse(errorMessage).toResponseEntity();
        } catch (CustomException ce) {
            return BaseResponse.getClientErrorResponse(ce.getMessage()).toResponseEntity();
        } catch (Exception ex) {
            return BaseResponse.getErrorResponse(ex.getMessage()).toResponseEntity();
        }
    }

    private EcommUserDetails validateUserCredentials(LoginClientRequest request) throws CustomException {
        EcommUserDetails userDetails = userDetailsRepository.getByEmail(request.getEmail());
        if (userDetails == null) {
            throw new CustomException(ERROR_USER_NOT_EXIST);
        }
        if (!CommonUtils.encodeMD5(request.getPassword()).equals(userDetails.getPassword())) {
            throw new CustomException(ERROR_INVALID_PASSWORD);
        }
        return userDetails;
    }

    private void updateRedisSession(String sessionId, EcommUserDetails userDetails, String email, String token) throws CustomException, JSONException {
        Object sessionDetails = redisService.getValue(sessionId);
        if (sessionDetails == null) {
            throw new CustomException(ERROR_INVALID_SESSION);
        }
        JSONObject sessionJson = new JSONObject(String.valueOf(sessionDetails));
        sessionJson.put("email", email);
        sessionJson.put("userId", userDetails.getId());
        sessionJson.put("userName", userDetails.getUserName());
        sessionJson.put("isLoggedIn", true);
        sessionJson.put("token", token);
        redisService.saveValueWithExpiry(sessionId, sessionJson, SESSION_EXPIRY_TIME_SECONDS);
    }

    private RegisterUserClientResponse prepareLoginResponse(EcommUserDetails userDetails) {
        RegisterUserClientResponse response = new RegisterUserClientResponse();
        response.setEmail(userDetails.getEmail());
        response.setUserId(userDetails.getId());
        response.setUserName(userDetails.getUserName());
        response.setMobile(userDetails.getMobile());
        response.setLastName(userDetails.getLastName());
        response.setFirstName(userDetails.getFirstName());
        return response;
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

    @Override
    public ResponseEntity<BaseResponse> profileDetails(String userId) {
        ProfileDetailsClientResponse response = new ProfileDetailsClientResponse();
        try {
            Optional<EcommUserDetails> userDetails = userDetailsRepository.findById(userId);
            if (userDetails.isEmpty()) {
                throw new CustomException(ERROR_USER_NOT_EXIST);
            }
            response.setEmail(userDetails.get().getEmail());
            response.setFirstName(userDetails.get().getFirstName());
            response.setLastName(userDetails.get().getLastName());
            response.setMobile(userDetails.get().getMobile());
            response.setUserId(userDetails.get().getId());
            response.setUserName(userDetails.get().getUserName());
            List<EcommUserAddressDetails> addressDetails = userAddressDetailsRepository.findAllByUserId(userId);
            List<Address> addressList = addressDetails.stream().map(ecommUserAddressDetails -> {
                Address address = new Address();
                address.setAddressLine1(ecommUserAddressDetails.getAddressLine1());
                address.setAddressLine2(ecommUserAddressDetails.getAddressLine2());
                address.setCity(ecommUserAddressDetails.getCity());
                address.setCountry(ecommUserAddressDetails.getCountry());
                address.setId(ecommUserAddressDetails.getId());
                address.setPrimary(ecommUserAddressDetails.getPrimary());
                address.setPinCode(ecommUserAddressDetails.getPinCode());
                address.setMobile(ecommUserAddressDetails.getMobile());
                address.setState(ecommUserAddressDetails.getState());
                return address;
            }).toList();
            response.setAddressList(addressList);
            return BaseResponse.getSuccessResponse(response).toResponseEntity();
        } catch (CustomException ce) {
            return BaseResponse.getClientErrorResponse(ce.getMessage()).toResponseEntity();
        } catch (Exception ex) {
            return BaseResponse.getErrorResponse(ex.getMessage()).toResponseEntity();
        }
    }

    private JSONObject createSessionResponseJson(String sessionId) throws JSONException {
        JSONObject responseJson = new JSONObject();
        responseJson.put("sessionId", sessionId);
        responseJson.put("timeout", SESSION_EXPIRY_TIME_SECONDS);
        responseJson.put("data", JsonUtils.getBeanByJson(redisService.getValue(sessionId).toString(), Object.class));
        return responseJson;
    }

    @Override
    public ResponseEntity<BaseResponse> updateAddress(String userId, String action, Address address) {
        try {
            List<EcommUserAddressDetails> existingAddresses = userAddressDetailsRepository.findAllByUserId(userId);

            switch (action.toLowerCase()) {
                case "update" -> {
                    handleUpdate(existingAddresses, address, userId);
                }
                case "delete" -> handleDelete(existingAddresses, address);
                default -> throw new CustomException("Invalid action specified. Use 'update' or 'delete'.");
            }
            ensureSinglePrimary(existingAddresses);
            List<EcommUserAddressDetails> updatedAddresses = userAddressDetailsRepository.saveAll(existingAddresses);
            List<Address> responseAddresses = updatedAddresses.stream()
                    .map(this::convertToResponseAddress)
                    .toList();

            userAddressDetailsRepository.saveAll(existingAddresses);
            return BaseResponse.getSuccessResponse(responseAddresses).toResponseEntity();

        } catch (CustomException ex) {
            return BaseResponse.getErrorResponse(ex.getMessage()).toResponseEntity();
        } catch (Exception ex) {
            return BaseResponse.getErrorResponse(ex.getMessage()).toResponseEntity();
        }
    }

    private void handleUpdate(List<EcommUserAddressDetails> existingAddresses, Address address, String userId) throws CustomException {
        if (Boolean.TRUE.equals(address.getPrimary())) {
            existingAddresses.forEach(addr -> addr.setPrimary(false));
        }
        if (address.getId() == null) {
            EcommUserAddressDetails newAddress = getEcommUserAddressDetails(address, userId);
            newAddress.setId(UUID.randomUUID().toString());
            newAddress.setPrimary(existingAddresses.isEmpty() || address.getPrimary() == null || address.getPrimary());
            existingAddresses.add(newAddress);
        } else {
            boolean updated = false;
            for (EcommUserAddressDetails existingAddress : existingAddresses) {
                if (existingAddress.getId().equals(address.getId())) {
                    updateExistingAddress(existingAddress, address);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                throw new CustomException("Specified address to update does not exist.");
            }

        }
    }

    private void handleDelete(List<EcommUserAddressDetails> existingAddresses, Address address) throws CustomException {
        if (address.getId() == null) {
            throw new CustomException("Address ID is required for deletion.");
        }

        if (existingAddresses.size() <= 1) {
            throw new CustomException("Cannot delete address as there must be at least one saved address.");
        }
        boolean deleted = existingAddresses.removeIf(addr -> addr.getId().equals(address.getId()));
        userAddressDetailsRepository.deleteById(address.getId());
        if (!deleted) {
            throw new CustomException("The address to be deleted does not exist.");
        }
    }

    private void ensureSinglePrimary(List<EcommUserAddressDetails> addressList) {
        long primaryCount = addressList.stream().filter(EcommUserAddressDetails::getPrimary).count();

        if (primaryCount == 0 && !addressList.isEmpty()) {
            addressList.get(0).setPrimary(true);
        } else if (primaryCount > 1) {
            boolean primarySet = false;
            for (EcommUserAddressDetails addr : addressList) {
                if (addr.getPrimary()) {
                    if (!primarySet) {
                        primarySet = true;
                    } else {
                        addr.setPrimary(false);
                    }
                }
            }
        }
    }

    private void updateExistingAddress(EcommUserAddressDetails existingAddress, Address updatedAddress) {
        existingAddress.setAddressLine1(updatedAddress.getAddressLine1());
        existingAddress.setAddressLine2(updatedAddress.getAddressLine2());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setState(updatedAddress.getState());
        existingAddress.setCountry(updatedAddress.getCountry());
        existingAddress.setPinCode(updatedAddress.getPinCode());
        existingAddress.setMobile(updatedAddress.getMobile());
        existingAddress.setPrimary(updatedAddress.getPrimary());
    }

    private Address convertToResponseAddress(EcommUserAddressDetails ecommUserAddressDetails) {
        Address address = new Address();
        address.setId(ecommUserAddressDetails.getId());
        address.setAddressLine1(ecommUserAddressDetails.getAddressLine1());
        address.setAddressLine2(ecommUserAddressDetails.getAddressLine2());
        address.setCity(ecommUserAddressDetails.getCity());
        address.setState(ecommUserAddressDetails.getState());
        address.setCountry(ecommUserAddressDetails.getCountry());
        address.setPinCode(ecommUserAddressDetails.getPinCode());
        address.setMobile(ecommUserAddressDetails.getMobile());
        address.setPrimary(ecommUserAddressDetails.getPrimary());
        return address;
    }
}