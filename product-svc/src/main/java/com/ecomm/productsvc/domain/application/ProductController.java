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
    public ProductController(@Qualifier("fakeStoreService") ProductService productService) {
        this.productService = productService;
    }

    // Fetch all products
    @GetMapping
    public ResponseEntity<BaseResponse> getAllProducts() {
        return productService.fetchAllProducts();
    }

    // Fetch a single product by ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable Long id) {
        return productService.fetchProductById(id);
    }

    // Fetch all categories
    @GetMapping("/categories")
    public ResponseEntity<BaseResponse> getAllCategories() {
        return productService.fetchAllCategories();
    }

    // Fetch products by category
    @GetMapping("/categories/{category}")
    public ResponseEntity<BaseResponse> getProductsByCategory(@PathVariable String category) {
        return productService.fetchProductsByCategory(category);
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<BaseResponse> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Modify an existing product
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> modifyProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.modifyProduct(id, product);
    }

    // Remove a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> removeProduct(@PathVariable Long id) {
        return productService.removeProduct(id);
    }
}
