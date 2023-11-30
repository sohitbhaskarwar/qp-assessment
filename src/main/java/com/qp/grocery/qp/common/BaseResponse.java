package com.qp.grocery.qp.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> extends ResponseEntity<T> {
    private HttpStatus status;
    private T data;

    public BaseResponse( T data, HttpStatus httpstatus) {
        super(data, httpstatus);
    }


}
