package com.qp.grocery.qp.service;

import com.qp.grocery.qp.request.OrderRequest;
import com.qp.grocery.qp.response.OrderResponse;

import java.util.List;

public interface OrderService {
    public OrderResponse placeMultipleOrder(OrderRequest orderRequest);
}
