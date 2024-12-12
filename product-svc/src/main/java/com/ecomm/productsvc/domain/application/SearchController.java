package com.ecomm.productsvc.domain.application;


import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.productsvc.domain.models.internal.SearchClientRequest;
import com.ecomm.productsvc.domain.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @PostMapping("/product")
    public ResponseEntity<BaseResponse> searchProduct( @RequestBody SearchClientRequest request){
        return searchService.searchProducts(request);
    }
}
