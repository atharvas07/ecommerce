package com.ecomm.paymentsvc.domain.application;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.mircrosvclib.utils.JsonUtils;
import com.ecomm.paymentsvc.domain.models.external.RazorPayWebhookRequest;
import com.ecomm.paymentsvc.domain.services.PaymentService;
import com.ecomm.paymentsvc.domain.services.RazorPayService;
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

    @GetMapping("/initiate-payment")
    public ResponseEntity<BaseResponse> initiatePayment(){
        return paymentService.createPaymentLink();
    }

    @PostMapping("/webhook")
    public void paymentDone(@RequestBody Object request) {
        System.out.println(JsonUtils.getJSON(request));
        razorPayService.webhook(JsonUtils.getBeanByObject(request, RazorPayWebhookRequest.class));
    }
}
