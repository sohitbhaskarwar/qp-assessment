package com.qp.grocery.qp.service;

import com.qp.grocery.qp.common.BaseResponse;
import com.qp.grocery.qp.request.ProductRequest;
import com.qp.grocery.qp.request.ProductUpdateRequest;
import com.qp.grocery.qp.response.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroceryService {
    ProductResponse addGroceryProduct(ProductRequest productRequest);

    List<ProductResponse> fetchAllGroceryDetails(Boolean isActive);

    ProductResponse updateProductDetails(Long id, ProductUpdateRequest productUpdateRequest);

    String deleteProduct(Long id);
}
