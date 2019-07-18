package com.androidtest.santander.exception;

public class UnsupportedInitializationException extends RuntimeException {

    public UnsupportedInitializationException() {
        super("Use instance() method instead");
    }

}
