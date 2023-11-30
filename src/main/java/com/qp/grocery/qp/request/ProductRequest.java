package com.qp.grocery.qp.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {
    @NotEmpty
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer inventory;
}
