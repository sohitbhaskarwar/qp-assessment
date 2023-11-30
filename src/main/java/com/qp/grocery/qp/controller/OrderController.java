package com.qp.grocery.qp.controller;

import com.qp.grocery.qp.annotation.RoleRequired;
import com.qp.grocery.qp.common.BaseResponse;
import com.qp.grocery.qp.enums.Role;
import com.qp.grocery.qp.request.OrderRequest;
import com.qp.grocery.qp.response.OrderResponse;
import com.qp.grocery.qp.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Log4j2
@RequestMapping("/api/v1/gbs/order")
public class OrderController {

    @Autowired
    private OrderService iOrderService;

    @PostMapping("/place")
    @RoleRequired(Role.USER)
    public BaseResponse<OrderResponse> placeMultipleOrder(@RequestBody OrderRequest orderRequest) {
        return new BaseResponse<>(iOrderService.placeMultipleOrder(orderRequest), HttpStatus.ACCEPTED);
    }

}
