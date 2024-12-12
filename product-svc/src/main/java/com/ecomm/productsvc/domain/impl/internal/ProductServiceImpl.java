package com.ecomm.productsvc.domain.impl.internal;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.productsvc.domain.models.external.Product;
import com.ecomm.productsvc.domain.models.external.Rating;
import com.ecomm.productsvc.domain.services.ProductService;
import com.ecomm.productsvc.domain.shared.entities.EcommProductCategoryMaster;
import com.ecomm.productsvc.domain.shared.entities.EcommProductDetails;
import com.ecomm.productsvc.domain.shared.entities.ProductInfoProjection;
import com.ecomm.productsvc.domain.shared.repositories.EcommProductCategoriesRepository;
import com.ecomm.productsvc.domain.shared.repositories.EcommProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("internalProductService")
public class ProductServiceImpl implements ProductService {

    private final EcommProductDetailsRepository productDetailsRepository;
    private final EcommProductCategoriesRepository productCategoriesRepository;

    @Autowired
    public ProductServiceImpl(EcommProductDetailsRepository productDetailsRepository,
                              EcommProductCategoriesRepository productCategoriesRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.productCategoriesRepository = productCategoriesRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAllProducts() {
        try {
            List<ProductInfoProjection> productsList = productDetailsRepository.findAllProductsWithCategory();
            List<Product> products = productsList.stream().map(this::mapToProduct).toList();
            return BaseResponse.getSuccessResponse(products).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> fetchProductById(Long id) {
        try {
            ProductInfoProjection productInfo = productDetailsRepository.findAllProductsByIdAndIsDeletedIsFalse(id);
            if (productInfo == null) {
                return BaseResponse.getErrorResponse("Product not found").toResponseEntity();
            }
            return BaseResponse.getSuccessResponse(mapToProduct(productInfo)).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse("An error occurred while fetching the product: " + e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAllCategories() {
        try {
            List<String> categories = productCategoriesRepository.findAll()
                    .stream()
                    .map(EcommProductCategoryMaster::getCategoryName)
                    .toList();
            return BaseResponse.getSuccessResponse(categories).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> fetchProductsByCategory(String category) {
        try {
            List<ProductInfoProjection> productsList = productDetailsRepository.findProductsByCategory(category);
            List<Product> products = productsList.stream().map(this::mapToProduct).toList();
            return BaseResponse.getSuccessResponse(products).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse( e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> createProduct(Product product) {
        try {
            EcommProductCategoryMaster category = findOrCreateCategory(product.getCategory());
            EcommProductDetails productEntity = mapToProductEntity(product, new EcommProductDetails(), category);
            EcommProductDetails savedProduct = productDetailsRepository.save(productEntity);
            return BaseResponse.getSuccessResponse(mapToProduct(savedProduct)).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> modifyProduct(Long id, Product product) {
        try {
            if (!productDetailsRepository.existsById(id)) {
                return BaseResponse.getErrorResponse("Product not found").toResponseEntity();
            }
            EcommProductDetails existingProduct = productDetailsRepository.findByIdAndIsDeletedFalse(id)
                    .orElseThrow(() -> new IllegalStateException("Product not found"));
            EcommProductCategoryMaster category = findOrCreateCategory(product.getCategory());
            mapToProductEntity(product, existingProduct, category);
            EcommProductDetails updatedProduct = productDetailsRepository.save(existingProduct);
            return BaseResponse.getSuccessResponse(mapToProduct(updatedProduct)).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> removeProduct(Long id) {
        try {
            if (!productDetailsRepository.existsByIdAndIsDeletedIsFalse(id)) {
                return BaseResponse.getErrorResponse("Product not found").toResponseEntity();
            }
            productDetailsRepository.updateIsDeletedById(id);
            return BaseResponse.getSuccessResponse("Product removed successfully").toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    private Product mapToProduct(ProductInfoProjection productInfo) {
        Product product = new Product();
        product.setCategory(productInfo.getCategory().getCategoryName());
        product.setId(productInfo.getId());
        product.setDescription(productInfo.getDescription());
        product.setRating(new Rating(productInfo.getRating(), productInfo.getRatingCount()));
        product.setImage(productInfo.getImageUrl());
        product.setTitle(productInfo.getTitle());
        product.setPrice(productInfo.getPrice());
        product.setQuantity(productInfo.getQuantity());
        return product;
    }

    private EcommProductDetails mapToProductEntity(Product product, EcommProductDetails productEntity, EcommProductCategoryMaster category) {
        productEntity.setCategory(category);
        productEntity.setDescription(product.getDescription());
        productEntity.setImageUrl(product.getImage());
        productEntity.setTitle(product.getTitle());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        return productEntity;
    }

    private EcommProductCategoryMaster findOrCreateCategory(String categoryName) {
        return productCategoriesRepository.findByCategoryName(categoryName)
                .orElseGet(() -> {
                    EcommProductCategoryMaster newCategory = new EcommProductCategoryMaster();
                    newCategory.setCategoryName(categoryName);
                    return productCategoriesRepository.save(newCategory);
                });
    }

    private Product mapToProduct(EcommProductDetails productInfo) {
        Product product = new Product();
        product.setCategory(productInfo.getCategory().getCategoryName());
        product.setId(productInfo.getId());
        product.setDescription(productInfo.getDescription());
        product.setRating(new Rating(productInfo.getRating(), productInfo.getRatingCount()));
        product.setImage(productInfo.getImageUrl());
        product.setTitle(productInfo.getTitle());
        product.setPrice(productInfo.getPrice());
        product.setQuantity(productInfo.getQuantity());
        return product;
    }
}
