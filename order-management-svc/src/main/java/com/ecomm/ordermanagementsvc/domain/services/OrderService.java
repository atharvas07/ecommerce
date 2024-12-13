package com.ecomm.ordermanagementsvc.domain.services;

import com.ecomm.mircrosvclib.models.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<BaseResponse> orderDetails(String orderId, String userId);

    ResponseEntity<BaseResponse> getAllOrders(String userId);
}
