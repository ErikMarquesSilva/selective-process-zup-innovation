package com.selectiveprocess.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceMandatoryException extends RuntimeException {
    public ResourceMandatoryException(String message) {
        super(message);
    }

    public ResourceMandatoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
