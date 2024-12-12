package com.ecomm.productsvc.domain.services;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.productsvc.domain.models.internal.SearchClientRequest;
import org.springframework.http.ResponseEntity;

public interface SearchService {

    ResponseEntity<BaseResponse> searchProducts(SearchClientRequest request);
}
