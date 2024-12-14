package com.ecomm.ordermanagementsvc.domain.shared.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service", url = "http://localhost:5002/payment")
@Component
public interface PaymentServiceProxy {

    @GetMapping("/pg/generate")
    String getPaymentLink(
            @RequestParam String userId, @RequestParam String customerName, @RequestParam String customerEmail,
            @RequestParam String customerContact, @RequestParam double amount, @RequestParam String orderId);
}
