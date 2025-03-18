package com.myevent.common.exception;

import lombok.Getter;

@Getter
public class NetworkScanException extends RuntimeException {

    private ErrorDictionary error;

    public NetworkScanException() {
        super("Falha na varredura da rede.");
    }

    public NetworkScanException(ErrorDictionary error) {
        this.error = error;
    }

    public NetworkScanException(String message) {
        super(message);
    }

}
