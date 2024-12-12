package com.ecomm.productsvc.domain.application;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.productsvc.domain.models.external.Product;
import com.ecomm.productsvc.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("internalProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllProducts() {
        return productService.fetchAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable Long id) {
        return productService.fetchProductById(id);
    }

    @GetMapping("/categories")
    public ResponseEntity<BaseResponse> getAllCategories() {
        return productService.fetchAllCategories();
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<BaseResponse> getProductsByCategory(@PathVariable String category) {
        return productService.fetchProductsByCategory(category);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> modifyProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.modifyProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> removeProduct(@PathVariable Long id) {
        return productService.removeProduct(id);
    }
}
