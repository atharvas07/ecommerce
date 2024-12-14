package com.ecomm.ordermanagementsvc.domain.impl;

import com.ecomm.mircrosvclib.exception.CustomException;
import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.mircrosvclib.utils.CommonUtils;
import com.ecomm.ordermanagementsvc.domain.models.CheckoutCartClientRequest;
import com.ecomm.ordermanagementsvc.domain.models.UpdateCartClientRequest;
import com.ecomm.ordermanagementsvc.domain.services.CartService;
import com.ecomm.ordermanagementsvc.domain.shared.entities.*;
import com.ecomm.ordermanagementsvc.domain.shared.proxy.PaymentServiceProxy;
import com.ecomm.ordermanagementsvc.domain.shared.repositories.*;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.ecomm.mircrosvclib.utils.EnumUtils.ProductDeliveryStatus.INITIATED;
import static com.ecomm.mircrosvclib.utils.EnumUtils.RazorpayPaymentStatus.CREATED;
import static org.apache.logging.log4j.LogManager.getLogger;

@Service
public class CartServiceImpl implements CartService {

    private final Logger logger = getLogger(CartServiceImpl.class);
    private final EcommUserCartRepository ecommUserCartRepository;
    private final CartDetailsMongoRepository cartDetailsMongoRepository;
    private final EcommProductDetailsRepository ecommProductDetailsRepository;
    private final PaymentServiceProxy paymentServiceProxy;
    private final EcommUserDetailsRepository ecommUserDetailsRepository;
    private final EcommOrderDetailsRepository ecommOrderDetailsRepository;
    private final EcommPurchaseProductsRepository ecommPurchaseProductsRepository;

    public CartServiceImpl(EcommUserCartRepository ecommUserCartRepository, CartDetailsMongoRepository cartDetailsMongoRepository, EcommProductDetailsRepository ecommProductDetailsRepository, PaymentServiceProxy paymentServiceProxy, EcommUserDetailsRepository ecommUserDetailsRepository, EcommOrderDetailsRepository ecommOrderDetailsRepository, EcommPurchaseProductsRepository ecommPurchaseProductsRepository) {
        this.ecommUserCartRepository = ecommUserCartRepository;
        this.cartDetailsMongoRepository = cartDetailsMongoRepository;
        this.ecommProductDetailsRepository = ecommProductDetailsRepository;
        this.paymentServiceProxy = paymentServiceProxy;
        this.ecommUserDetailsRepository = ecommUserDetailsRepository;
        this.ecommOrderDetailsRepository = ecommOrderDetailsRepository;
        this.ecommPurchaseProductsRepository = ecommPurchaseProductsRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> updateCart(UpdateCartClientRequest request, String userId) {
        try {
            Optional<EcommUserCartDetail> optionalUserCart = ecommUserCartRepository.findByUserIdAndIsActive(userId, true);
            EcommUserCartDetail currentUserCart = optionalUserCart.orElseGet(() -> {
                EcommUserCartDetail newUserCart = new EcommUserCartDetail();
                newUserCart.setUserId(userId);
                newUserCart.setIsActive(true);
                newUserCart.setId(UUID.randomUUID().toString());
                newUserCart.setUpdatedAt(Instant.now());
                return newUserCart;
            });
            String cartId = currentUserCart.getId();
            currentUserCart.setUpdatedAt(Instant.now());

            MongoCartDetails cartDetails = cartDetailsMongoRepository.findById(cartId)
                    .orElseGet(() -> {
                        MongoCartDetails newCartDetails = new MongoCartDetails();
                        newCartDetails.setId(cartId);
                        newCartDetails.setUserId(userId);
                        return newCartDetails;
                    });
            List<MongoCartDetails.Product> currentProducts = new ArrayList<>();
            MongoCartDetails savedCartDetails = new MongoCartDetails();
            switch (request.getAction()) {
                case "add" -> {
                    cartDetails.setId(cartId);
                    cartDetails.setUserId(userId);
                    if (cartDetails.getProductsList() != null) {
                        currentProducts.addAll(cartDetails.getProductsList());
                    }
                    request.getProducts().forEach(productsItem ->
                            ecommProductDetailsRepository.findById(productsItem.getProductId())
                                    .ifPresent(productDetail -> {
                                        productsItem.setId(UUID.randomUUID().toString());
                                        productsItem.setDescription(productDetail.getDescription());
                                        productsItem.setPrice(productDetail.getPrice());
                                        productsItem.setTitle(productDetail.getTitle());
                                        productsItem.setImage(productDetail.getImageUrl());
                                    })
                    );
                    currentProducts.addAll(request.getProducts());
                    cartDetails.setProductsList(currentProducts);
                    double totalAmount = currentProducts.stream().mapToDouble(MongoCartDetails.Product::getPrice).sum();
                    cartDetails.setTotalQuantity(currentProducts.size());
                    cartDetails.setTotalAmount(totalAmount);
                    savedCartDetails = cartDetailsMongoRepository.save(cartDetails);
                }
                case "remove" -> {
                    Set<String> productIdsToRemove = request.getProducts().stream()
                            .map(MongoCartDetails.Product::getId)
                            .collect(Collectors.toSet());
                    cartDetails.getProductsList().removeIf(product -> productIdsToRemove.contains(product.getId()));
                    double totalAmount = cartDetails.getProductsList().stream()
                            .mapToDouble(MongoCartDetails.Product::getPrice)
                            .sum();
                    cartDetails.setTotalQuantity(cartDetails.getProductsList().size());
                    cartDetails.setTotalAmount(totalAmount);
                    savedCartDetails = cartDetailsMongoRepository.save(cartDetails);
                }
            }
            currentUserCart.setUpdatedAt(Instant.now());
            ecommUserCartRepository.save(currentUserCart);

            return BaseResponse.getSuccessResponse(savedCartDetails).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getCart(String userId) {
        try {
            Optional<EcommUserCartDetail> optionalUserCart = ecommUserCartRepository.findByUserIdAndIsActive(userId, true);
            if (optionalUserCart.isEmpty()) {
                MongoCartDetails emptyCartDetails = new MongoCartDetails();
                emptyCartDetails.setProductsList(new ArrayList<>());
                emptyCartDetails.setTotalQuantity(0);
                emptyCartDetails.setTotalAmount(0.0);
                return BaseResponse.getSuccessResponse(emptyCartDetails).toResponseEntity();
            }
            Optional<MongoCartDetails> cartDetails = cartDetailsMongoRepository.findById(optionalUserCart.get().getId());
            return BaseResponse.getSuccessResponse(cartDetails).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> checkoutCart(String userId, CheckoutCartClientRequest request) {
        try {
            String orderId = CommonUtils.generateOrderId();

            //create order in db
            //set cart details
            Optional<EcommUserCartDetail> userCart = ecommUserCartRepository.findByUserIdAndIsActive(userId, true);
            if (userCart.isEmpty()) throw new CustomException("Cart is empty");

            Optional<EcommUserDetail> userDetail = ecommUserDetailsRepository.findById(userId);
            if (userDetail.isEmpty()) throw new CustomException("User not found");

            AtomicInteger counter = new AtomicInteger(1);
            Set<EcommPurchaseProductDetail> purchaseDetails = new HashSet<>();
            Optional<MongoCartDetails> cart = cartDetailsMongoRepository.findById(userCart.get().getId());

            if (cart.isEmpty() || cart.get().getProductsList().isEmpty()) throw new CustomException("Cart is empty");

            cart.get().getProductsList().forEach(product -> {
                EcommPurchaseProductDetail purchaseProductDetail = new EcommPurchaseProductDetail();
                purchaseProductDetail.setId(orderId + "_" + counter.getAndIncrement());
                purchaseProductDetail.setProductId(product.getProductId());
                purchaseProductDetail.setQuantity(1);
                purchaseProductDetail.setAmount(product.getPrice());
                purchaseProductDetail.setCurrentStatus(String.valueOf(INITIATED));
                purchaseProductDetail.setOrderId(orderId);
                purchaseDetails.add(purchaseProductDetail);
            });

            EcommOrderDetail orderDetail = new EcommOrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setCurrency("INR");
            orderDetail.setAmount(cart.get().getTotalAmount());
            orderDetail.setStatus(String.valueOf(INITIATED));
            orderDetail.setPaymentStatus(CREATED.getStatus());
            orderDetail.setShippingAddressId(request.getAddress().getShipping().getId());
            orderDetail.setBillingAddressId(request.getAddress().getBilling().getId());
            orderDetail.setUserId(userId);

            //save order details
            ecommOrderDetailsRepository.save(orderDetail);
            ecommPurchaseProductsRepository.saveAll(purchaseDetails);

            //generateUrl
            String paymentUrl = paymentServiceProxy.getPaymentLink(
                    userId,
                    userDetail.get().getFirstName() + " " + userDetail.get().getLastName(),
                    userDetail.get().getEmail(),
                    userDetail.get().getMobile().toString(),
                    cart.get().getTotalAmount(),
                    orderId
            );
            if (paymentUrl == null) throw new Exception();

            //update cart
            userCart.get().setIsActive(false);
            userCart.get().setUpdatedAt(Instant.now());
            ecommUserCartRepository.save(userCart.get());

            return BaseResponse.getSuccessResponse(paymentUrl).toResponseEntity();
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }

    }

}
