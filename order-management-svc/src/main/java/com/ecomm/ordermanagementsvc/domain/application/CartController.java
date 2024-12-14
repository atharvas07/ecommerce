package com.ecomm.ordermanagementsvc.domain.application;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.ordermanagementsvc.domain.models.CheckoutCartClientRequest;
import com.ecomm.ordermanagementsvc.domain.models.UpdateCartClientRequest;
import com.ecomm.ordermanagementsvc.domain.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/update")
    ResponseEntity<BaseResponse> addProduct(@RequestHeader("user-id") String userId, @RequestBody UpdateCartClientRequest request) {
        return cartService.updateCart(request, userId);
    }

    @GetMapping("")
    ResponseEntity<BaseResponse> getCart(@RequestHeader("user-id") String userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/checkout")
    ResponseEntity<BaseResponse> checkout(@RequestHeader("user-id") String userId, @RequestBody CheckoutCartClientRequest request) {
        return cartService.checkoutCart(userId, request);
    }
}
