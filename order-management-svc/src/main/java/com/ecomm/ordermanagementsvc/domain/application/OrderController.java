package com.ecomm.ordermanagementsvc.domain.application;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.ordermanagementsvc.domain.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/")
    public ResponseEntity<BaseResponse> getOrderDetails(@RequestHeader("user-id") String userId){
        return orderService.getAllOrders(userId);
    }

    @GetMapping("/details/{orderId}")
    public ResponseEntity<BaseResponse> getOrderDetails(@RequestHeader("user-id") String userId, @PathVariable String orderId){
        return orderService.orderDetails(orderId, userId);
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<BaseResponse> getOrderStatus(@RequestHeader("user-id") String userId, @PathVariable String orderId){
        return orderService.getOrderStatus(orderId);
    }

    @PostMapping("/status/updates/{orderId}")
    public ResponseEntity<BaseResponse> updateOrderStatus(@PathVariable String orderId) {
        return orderService.updateOrderStatus(orderId);
    }

    @GetMapping("/payment/updates/{orderId}")
    public ResponseEntity<BaseResponse> updatePaymentDetails(@PathVariable String orderId, @RequestParam String transactionId, @RequestParam String paymentStatus) {
        return orderService.updatePaymentDetails(orderId, transactionId, paymentStatus);
    }


}
