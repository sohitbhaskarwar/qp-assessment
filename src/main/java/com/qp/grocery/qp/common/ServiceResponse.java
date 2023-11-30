package com.qp.grocery.qp.common;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ServiceResponse<T> extends ResponseEntity<T> {
    public ServiceResponse(HttpStatusCode status) {
        super(status);
    }

    public ServiceResponse(T body, HttpStatusCode status) {
        super(body, status);
    }
}
