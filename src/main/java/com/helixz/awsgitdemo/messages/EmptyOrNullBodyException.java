package com.helixz.awsgitdemo.messages;

public class EmptyOrNullBodyException extends RuntimeException {
    public EmptyOrNullBodyException(String message) {
        super(message);
    }
}
