package com.ecomm.productsvc.domain.impl.external;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.productsvc.domain.clients.FakeStoreProductClient;
import com.ecomm.productsvc.domain.models.external.Product;
import com.ecomm.productsvc.domain.services.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakeStoreService")
public class ProductServiceImpl implements ProductService {

    private final FakeStoreProductClient productClient;


    public ProductServiceImpl(FakeStoreProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    @Cacheable("products")
    public ResponseEntity<BaseResponse> fetchAllProducts() {
        try{
            List<Product> products = productClient.getAllProducts();
            return BaseResponse.getSuccessResponse(products).toResponseEntity();
        }catch (Exception e){
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> fetchProductById(Long id) {
        try {
            Product product = productClient.getProductById(id);
            return BaseResponse.getSuccessResponse(product).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAllCategories() {
        try {
            List<String> categories = productClient.getAllCategories();
            return BaseResponse.getSuccessResponse(categories).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> fetchProductsByCategory(String category) {
        try {
            List<Product> products = productClient.getProductsByCategory(category);
            return BaseResponse.getSuccessResponse(products).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> createProduct(Product product) {
        try {
            Product createdProduct = productClient.addProduct(product);
            return BaseResponse.getSuccessResponse(createdProduct).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> modifyProduct(Long id, Product product) {
        try {
            Product updatedProduct = productClient.updateProduct(id, product);
            return BaseResponse.getSuccessResponse(updatedProduct).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> removeProduct(Long id) {
        try {
            productClient.deleteProduct(id);
            return BaseResponse.getSuccessResponse("Product successfully deleted").toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }
}
