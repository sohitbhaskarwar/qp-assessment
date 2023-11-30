package com.qp.grocery.qp.controller;

import com.qp.grocery.qp.annotation.RoleRequired;
import com.qp.grocery.qp.common.BaseResponse;
import com.qp.grocery.qp.common.ServiceResponse;
import com.qp.grocery.qp.enums.Role;
import com.qp.grocery.qp.request.ProductRequest;
import com.qp.grocery.qp.request.ProductUpdateRequest;
import com.qp.grocery.qp.response.ProductResponse;
import com.qp.grocery.qp.service.GroceryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/*
APP Id: 29382181
Design a Grocery Booking API:
        Roles:
        - Admin
        - User
        Mandatory Requirements:
        Design API endpoints
        1. Admin Responsibilities:
        - Add new grocery items to the system
        - View existing grocery items
        - Remove grocery items from the system
        - Update details (e.g., name, price) of existing grocery items
        - Manage inventory levels of grocery items
        2. User Responsibilities:
        - View the list of available grocery items
        - Ability to book multiple grocery items in a single order*/
@RestController
@Log4j2
@RequestMapping("/api/v1/gbs")
public class GroceryController {

    @Autowired
    private GroceryService iGroceryService;

    @PostMapping("/add")
    @RoleRequired(Role.ADMIN)
    public BaseResponse<ProductResponse> addGroceryProduct(@RequestBody ProductRequest productRequest) {
        return new BaseResponse<>(iGroceryService.addGroceryProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping("all")
    @RoleRequired(Role.ADMIN)
    public BaseResponse<List<ProductResponse>> fetchAllGroceryDetails() {
        return new BaseResponse<>(iGroceryService.fetchAllGroceryDetails(), HttpStatus.OK);
    }

    @PutMapping()
    public BaseResponse<ProductResponse> updateProductDetails(@PathVariable(name = "id") @NotNull Long id,
                                                                    @RequestBody ProductUpdateRequest productUpdateRequest) {
        return new BaseResponse<>(iGroceryService.updateProductDetails(id, productUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping()
    @RoleRequired(Role.ADMIN)
    public BaseResponse<String> deleteProduct(@PathVariable(name = "id") @NotNull Long id) {
        return new BaseResponse<>(iGroceryService.deleteProduct(id), HttpStatus.OK);
    }
}
