package com.myevent.common.exception;


import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private ErrorDictionary error;

    public AuthException(ErrorDictionary error) {
        this.error = error;
    }

    public AuthException(String message) {
        super(message);
    }

}
