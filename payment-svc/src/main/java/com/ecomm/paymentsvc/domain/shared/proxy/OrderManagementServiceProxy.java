package com.ecomm.paymentsvc.domain.shared.proxy;

import com.ecomm.mircrosvclib.models.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-svc", url = "http://localhost:5003")
@Component
public interface OrderManagementServiceProxy {

    @GetMapping("/order/details/{orderId}")
    ResponseEntity<BaseResponse> getOrderDetails(
            @RequestHeader("user-id") String userId,
            @PathVariable("orderId") String orderId
    );

    @GetMapping("/payment/updates/{orderId}")
    ResponseEntity<BaseResponse> updatePaymentDetails(@PathVariable String orderId, @RequestParam String transactionId, @RequestParam String paymentStatus);
}
