package com.qp.grocery.qp.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductUpdateRequest {

    private String name;
    private Integer price;
    private Integer inventory;
}
