package com.ecomm.productsvc.domain.clients;

import com.ecomm.productsvc.domain.models.external.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "fakeStoreProductClient", url = "${fakestore.api.url}")
@Component
public interface FakeStoreProductClient {

    @GetMapping("/products")
    List<Product> getAllProducts();

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable("id") Long id);

    @GetMapping("/products/categories")
    List<String> getAllCategories();

    @GetMapping("/products/category/{category}")
    List<Product> getProductsByCategory(@PathVariable("category") String category);

    @PostMapping("/products")
    Product addProduct(@RequestBody Product product);

    @PutMapping("/products/{id}")
    Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product);

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable("id") Long id);
}
