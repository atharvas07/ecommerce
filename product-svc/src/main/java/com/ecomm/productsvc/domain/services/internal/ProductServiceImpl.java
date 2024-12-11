package com.ecomm.productsvc.domain.services.internal;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.productsvc.domain.models.external.Product;
import com.ecomm.productsvc.domain.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("internalProductService")
public class ProductServiceImpl implements ProductService {


    @Override
    public ResponseEntity<BaseResponse> fetchAllProducts() {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchProductById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAllCategories() {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchProductsByCategory(String category) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> createProduct(Product product) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> modifyProduct(Long id, Product product) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> removeProduct(Long id) {
        return null;
    }
}
