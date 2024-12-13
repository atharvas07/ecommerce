package com.ecomm.paymentsvc.domain.application;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.mircrosvclib.utils.JsonUtils;
import com.ecomm.paymentsvc.domain.models.external.RazorPayWebhookRequest;
import com.ecomm.paymentsvc.domain.services.PaymentService;
import com.ecomm.paymentsvc.domain.services.RazorPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final RazorPayService razorPayService;

    @Autowired
    public PaymentController(PaymentService paymentService, RazorPayService razorPayService) {
        this.paymentService = paymentService;
        this.razorPayService = razorPayService;
    }

    @PostMapping("/initiate-payment/{orderId}")
    public ResponseEntity<BaseResponse> initiatePayment(HttpServletRequest httpServletRequest, @PathVariable String orderId){
        String sessionId = httpServletRequest.getHeader("session-id");
        return paymentService.createPaymentLink(orderId, sessionId);
    }

    @PostMapping("/webhook")
    public void paymentDone(@RequestBody Object request) {
        razorPayService.webhook(JsonUtils.getBeanByObject(request, RazorPayWebhookRequest.class));
    }
}
