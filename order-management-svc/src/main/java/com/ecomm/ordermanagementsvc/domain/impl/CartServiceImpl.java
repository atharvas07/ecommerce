package com.ecomm.ordermanagementsvc.domain.impl;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.ordermanagementsvc.domain.models.UpdateCartClientRequest;
import com.ecomm.ordermanagementsvc.domain.services.CartService;
import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommUserCartDetail;
import com.ecomm.ordermanagementsvc.domain.shared.entities.MongoCartDetails;
import com.ecomm.ordermanagementsvc.domain.shared.repositories.CartDetailsMongoRepository;
import com.ecomm.ordermanagementsvc.domain.shared.repositories.EcommProductDetailsRepository;
import com.ecomm.ordermanagementsvc.domain.shared.repositories.EcommUserCartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final EcommUserCartRepository ecommUserCartRepository;
    private final CartDetailsMongoRepository cartDetailsMongoRepository;
    private final EcommProductDetailsRepository ecommProductDetailsRepository;

    public CartServiceImpl(EcommUserCartRepository ecommUserCartRepository, CartDetailsMongoRepository cartDetailsMongoRepository, EcommProductDetailsRepository ecommProductDetailsRepository) {
        this.ecommUserCartRepository = ecommUserCartRepository;
        this.cartDetailsMongoRepository = cartDetailsMongoRepository;
        this.ecommProductDetailsRepository = ecommProductDetailsRepository;
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

}
