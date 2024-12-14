package com.ecomm.ordermanagementsvc.domain.services;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.ordermanagementsvc.domain.models.CheckoutCartClientRequest;
import com.ecomm.ordermanagementsvc.domain.models.UpdateCartClientRequest;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<BaseResponse> updateCart(UpdateCartClientRequest request, String userId);

    ResponseEntity<BaseResponse> getCart(String userId);

    ResponseEntity<BaseResponse> checkoutCart(String userId, CheckoutCartClientRequest request);
}
