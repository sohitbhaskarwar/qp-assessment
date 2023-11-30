package com.qp.grocery.qp.util;

import com.qp.grocery.qp.entity.ProductEntity;
import com.qp.grocery.qp.response.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

public class EntityToResponseUtil {
    public static ProductResponse fetchCreateProductResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .inventory(productEntity.getInventory())
                .active(productEntity.getIsActive())
                .build();

    }

    public static List<ProductResponse> fetchAllProductDetails(List<ProductEntity> productEntityList) {
        return productEntityList.stream()
                .map(EntityToResponseUtil::fetchCreateProductResponse)
                .collect(Collectors.toList());
    }
}
