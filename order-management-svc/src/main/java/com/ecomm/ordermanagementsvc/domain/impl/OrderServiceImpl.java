package com.ecomm.ordermanagementsvc.domain.impl;

import com.ecomm.mircrosvclib.exception.CustomException;
import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.ordermanagementsvc.domain.services.OrderService;
import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommOrderDetailProjection;
import com.ecomm.ordermanagementsvc.domain.shared.repositories.EcommOrderDetailsRepository;
import com.ecomm.ordermanagementsvc.domain.shared.repositories.EcommPurchaseProductsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final EcommOrderDetailsRepository ecommOrderDetailsRepository;
    private final EcommPurchaseProductsRepository ecommPurchaseProductsRepository;

    public OrderServiceImpl(EcommOrderDetailsRepository ecommOrderDetailsRepository, EcommPurchaseProductsRepository ecommPurchaseProductsRepository) {
        this.ecommOrderDetailsRepository = ecommOrderDetailsRepository;
        this.ecommPurchaseProductsRepository = ecommPurchaseProductsRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> orderDetails(String orderId, String userId) {
        try {
            Optional<EcommOrderDetailProjection> orderDetail = ecommOrderDetailsRepository.getByOrderIdAndUser_Id(orderId, userId);

            EcommOrderDetailProjection detail = orderDetail.orElseThrow(() -> new CustomException("Order not found for ID: " + orderId));
            return BaseResponse.getSuccessResponse(detail).toResponseEntity();
        } catch (CustomException e) {
            return BaseResponse.getClientErrorResponse(e.getMessage()).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getAllOrders(String userId){
        try{
            List<EcommOrderDetailProjection> orders = ecommOrderDetailsRepository.getByUser_Id(userId);
            if(orders.isEmpty()) throw new CustomException("No orders found for user ID: " + userId);
            return BaseResponse.getSuccessResponse(orders).toResponseEntity();
        } catch (CustomException e) {
            return BaseResponse.getClientErrorResponse(e.getMessage()).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

}
