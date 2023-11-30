package com.qp.grocery.qp.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Integer price;
    private Integer inventory;
    private Boolean active;
}
