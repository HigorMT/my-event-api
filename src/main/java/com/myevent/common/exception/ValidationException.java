package com.myevent.common.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private ErrorDictionary error;

    public ValidationException(ErrorDictionary error) {
        this.error = error;
    }

    public ValidationException(String message) {
        super(message);
    }

}
