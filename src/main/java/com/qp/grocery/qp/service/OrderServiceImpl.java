package com.qp.grocery.qp.service;

import com.qp.grocery.qp.entity.ProductEntity;
import com.qp.grocery.qp.request.OrderRequest;
import com.qp.grocery.qp.request.OrderDetails;
import com.qp.grocery.qp.response.OrderResponse;
import com.qp.grocery.qp.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public OrderResponse placeMultipleOrder(OrderRequest orderRequest) {
        List<OrderDetails> successfulOrderList = new ArrayList<>();
        List<OrderDetails> failedOrderList = new ArrayList<>();
        for (OrderDetails orderRequestData : orderRequest.getOrders()) {
            Optional<ProductEntity> productEntityOptional =
                    productRepository.findById(orderRequestData.getProductId().longValue());
            if ((productEntityOptional.isPresent())
                    && (updateProductDetailsIfAvailable(productEntityOptional.get(), orderRequestData))) {
                orderRequestData.setProductName(productEntityOptional.get().getName());
                successfulOrderList.add(orderRequestData);
            } else {
                failedOrderList.add(orderRequestData);
            }
        }

        return new OrderResponse(successfulOrderList, failedOrderList);
    }

    private boolean updateProductDetailsIfAvailable(ProductEntity productEntity,
                                                    OrderDetails orderRequestData) {
        Integer availableUnits = productEntity.getInventory();
        Integer requestedUnits = orderRequestData.getProductUnit();
        if ((productEntity.getIsActive())
                && (availableUnits >= requestedUnits)) {
            productEntity.setInventory(availableUnits - requestedUnits);
            productRepository.save(productEntity);
            return true;
        }
        return false;
    }
}
