package com.ecomm.productsvc.domain.services;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.productsvc.domain.models.external.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<BaseResponse> fetchAllProducts();

    ResponseEntity<BaseResponse> fetchProductById(Long id);

    ResponseEntity<BaseResponse> fetchAllCategories();

    ResponseEntity<BaseResponse> fetchProductsByCategory(String category);

    ResponseEntity<BaseResponse> createProduct(Product product);

    ResponseEntity<BaseResponse> modifyProduct(Long id, Product product);

    ResponseEntity<BaseResponse> removeProduct(Long id);
}
