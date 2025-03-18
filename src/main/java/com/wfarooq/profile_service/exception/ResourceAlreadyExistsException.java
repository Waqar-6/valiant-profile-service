package com.wfarooq.profile_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String resource, String field, String value) {
        super(String.format("%s already exists with %s:'%s'", resource, field, value));
    }
}