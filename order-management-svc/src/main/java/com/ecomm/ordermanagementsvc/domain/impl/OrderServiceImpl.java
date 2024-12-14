package com.ecomm.ordermanagementsvc.domain.impl;

import com.ecomm.mircrosvclib.exception.CustomException;
import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.mircrosvclib.utils.EnumUtils;
import com.ecomm.ordermanagementsvc.domain.services.OrderService;
import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommOrderDetailProjection;
import com.ecomm.ordermanagementsvc.domain.shared.entities.EcommOrderStatusUpdate;
import com.ecomm.ordermanagementsvc.domain.shared.entities.OrderStatusProjection;
import com.ecomm.ordermanagementsvc.domain.shared.repositories.EcommOrderDetailsRepository;
import com.ecomm.ordermanagementsvc.domain.shared.repositories.EcommOrderStatusUpdateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final EcommOrderDetailsRepository ecommOrderDetailsRepository;
    private final EcommOrderStatusUpdateRepository ecommOrderStatusUpdateRepository;

    public OrderServiceImpl(EcommOrderDetailsRepository ecommOrderDetailsRepository, EcommOrderStatusUpdateRepository ecommOrderStatusUpdateRepository) {
        this.ecommOrderDetailsRepository = ecommOrderDetailsRepository;
        this.ecommOrderStatusUpdateRepository = ecommOrderStatusUpdateRepository;
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

    @Override
    public ResponseEntity<BaseResponse> getOrderStatus(String orderId) {
        try {
            List<OrderStatusProjection> orderStatusUpdates = ecommOrderStatusUpdateRepository.findAllByOrderIdOrderByCreatedAtDesc(orderId);
            if (orderStatusUpdates.isEmpty())
                throw new CustomException("No order status updates found for order ID: " + orderId);
            return BaseResponse.getSuccessResponse(orderStatusUpdates).toResponseEntity();
        } catch (CustomException e) {
            return BaseResponse.getClientErrorResponse(e.getMessage()).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> updateOrderStatus(String orderId) {
        try {
            EcommOrderStatusUpdate currentStatus = ecommOrderStatusUpdateRepository.getFirstByOrderIdOrderByCreatedAtDesc(orderId);
            EnumUtils.ProductDeliveryStatus nextStatus = EnumUtils.ProductDeliveryStatus.valueOf(currentStatus.getStatus()).getNextStatus();
            EcommOrderStatusUpdate newStatusUpdate = new EcommOrderStatusUpdate();
            newStatusUpdate.setOrderId(orderId);
            if (nextStatus != null) {
                newStatusUpdate.setStatus(nextStatus.name());
                newStatusUpdate.setStatusDescription(nextStatus.getDescription());
                EcommOrderStatusUpdate updatedStatus = ecommOrderStatusUpdateRepository.save(newStatusUpdate);
                return BaseResponse.getSuccessResponse(updatedStatus).toResponseEntity();
            }
            return BaseResponse.getSuccessResponse(currentStatus).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> updatePaymentDetails(String orderId, String transactionId, String paymentStatus) {
        try {
            ecommOrderDetailsRepository.updatePaymentDetails(orderId, transactionId, paymentStatus);
            return BaseResponse.getSuccessResponse("Success").toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

}
