package com.ecomm.paymentsvc.domain.services;

import com.ecomm.paymentsvc.domain.models.external.RazorPayGenerateLinkResponse;
import com.ecomm.paymentsvc.domain.models.external.RazorPayWebhookRequest;

public interface RazorPayService {

    RazorPayGenerateLinkResponse createPaymentLink(
            String userId, String customerName, String customerEmail,
            String customerContact, double amount, String orderId, String transactionId);

    void webhook(RazorPayWebhookRequest request);

}
