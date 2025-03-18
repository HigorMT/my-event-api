package com.myevent.common.exception;

public class FeignException extends RuntimeException {

    public FeignException(String message) {
        super(message);
    }

}
