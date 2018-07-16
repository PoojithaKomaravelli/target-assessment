package com.target.services.retail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by PKomaravelli on 7/11/2018.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RetailServiceException extends Exception {

    public RetailServiceException() {
        super();
    }

    public RetailServiceException(String message) {
        super(message);
    }

    public RetailServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetailServiceException(Throwable cause) {
        super(cause);
    }

}
