package com.ecomm.mircrosvclib.utils;

public class EnumUtils {

    public enum RazorpayPaymentStatus {
        CREATED("created"),
        AUTHORIZED("authorized"),
        CAPTURED("captured"),
        FAILED("failed"),
        REFUNDED("refunded"),
        PARTIALLY_REFUNDED("partially_refunded"),
        PROCESSING("processing"),
        PENDING("pending");

        private final String status;

        RazorpayPaymentStatus(String status) {
            this.status = status;
        }

        public static RazorpayPaymentStatus fromStatus(String status) {
            for (RazorpayPaymentStatus razorpayPaymentStatus : values()) {
                if (razorpayPaymentStatus.getStatus().equalsIgnoreCase(status)) {
                    return razorpayPaymentStatus;
                }
            }
            return null;
        }

        public static boolean isSuccess(RazorpayPaymentStatus status) {
            if (status == AUTHORIZED || status == CAPTURED) {
                return true;
            }
            return false;
        }

        public String getStatus() {
            return status;
        }
    }

    public enum ProductDeliveryStatus {

        INITIATED(1, "The order has been initiated."),
        ORDER_PLACED(2, "The order has been placed."),
        WAREHOUSE_PROCESSING(3, "The order is being packed and processed at the warehouse."),
        PICKED_BY_COURIER(4, "The product has been picked up by the courier or shipping partner."),
        IN_TRANSIT(5, "The product is in transit to the delivery destination."),
        OUT_FOR_DELIVERY(6, "The product is out for delivery to the customer's address."),
        DELIVERED(7, "The product has been successfully delivered to the customer."),

        PAYMENT_FAILED(10, "The payment for the order has been failed.");

        private final int sequenceNumber;
        private final String description;

        ProductDeliveryStatus(int sequenceNumber, String description) {
            this.sequenceNumber = sequenceNumber;
            this.description = description;
        }

        public static ProductDeliveryStatus getBySequenceNumber(int sequenceNumber) {
            for (ProductDeliveryStatus status : values()) {
                if (status.getSequenceNumber() == sequenceNumber) {
                    return status;
                }
            }
            return null;
        }

        public int getSequenceNumber() {
            return sequenceNumber;
        }

        public String getDescription() {
            return description;
        }

        public ProductDeliveryStatus getNextStatus() {
            for (ProductDeliveryStatus status : values()) {
                if (status.getSequenceNumber() == this.sequenceNumber + 1) {
                    return status;
                }
            }
            return null;
        }
    }
}
