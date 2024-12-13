package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cart_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoCartDetails {

    List<Product> productsList;
    @Id
    private String id;
    private String userId;
    private Double totalAmount;
    private Integer totalQuantity;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private String id;
        private Long productId;
        private String title;
        private double price;
        private String image;
        private String description;
    }
}
