package com.ecomm.productsvc.domain.impl.internal;


import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.productsvc.domain.models.external.Product;
import com.ecomm.productsvc.domain.models.external.Rating;
import com.ecomm.productsvc.domain.models.internal.SearchClientRequest;
import com.ecomm.productsvc.domain.models.internal.SearchClientResponse;
import com.ecomm.productsvc.domain.services.SearchService;
import com.ecomm.productsvc.domain.shared.entities.ProductInfoProjection;
import com.ecomm.productsvc.domain.shared.repositories.EcommProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    private final EcommProductDetailsRepository productDetailsRepository;

    @Autowired
    public SearchServiceImpl(EcommProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> searchProducts(SearchClientRequest request){
        try{
            Pageable pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by("title").descending());
            Page<ProductInfoProjection> potentialProductsList = productDetailsRepository.findByTitleContaining(request.getSearchTerm(), pageRequest);
            SearchClientResponse response = new SearchClientResponse();
            response.setProducts(potentialProductsList.getContent().stream().map(this::mapToProduct).toList());
            response.setTotalPages(potentialProductsList.getTotalPages());
            response.setTotalElements(potentialProductsList.getTotalElements());
            return BaseResponse.getSuccessResponse(response).toResponseEntity();
        }catch (Exception ex){
            return BaseResponse.getErrorResponse(ex.getMessage()).toResponseEntity();
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

}
