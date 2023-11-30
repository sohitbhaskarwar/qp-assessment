package com.qp.grocery.qp.util;

import com.qp.grocery.qp.entity.ProductEntity;
import com.qp.grocery.qp.request.ProductRequest;
import com.qp.grocery.qp.request.ProductUpdateRequest;

import java.util.Objects;
import java.util.Optional;

public class RequestToEntityUtil {

    public static ProductEntity fetchProductEntityFromRequest(ProductRequest productRequest) {
        return ProductEntity.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .isActive(true)
                .inventory(productRequest.getInventory())
                .build();
    }

    public static ProductEntity fetchProductEntityFromRequestForUpdate(
            ProductEntity existingProductEntity, ProductUpdateRequest productUpdateRequest) {

        existingProductEntity.setName(Objects.isNull(productUpdateRequest.getName())
                ? existingProductEntity.getName() : productUpdateRequest.getName());

        existingProductEntity.setPrice(Objects.isNull(productUpdateRequest.getPrice())
                ? existingProductEntity.getPrice() : productUpdateRequest.getPrice());

        existingProductEntity.setInventory(Objects.isNull(productUpdateRequest.getInventory())
                ? existingProductEntity.getInventory() : productUpdateRequest.getInventory());

        return existingProductEntity;
    }
}
