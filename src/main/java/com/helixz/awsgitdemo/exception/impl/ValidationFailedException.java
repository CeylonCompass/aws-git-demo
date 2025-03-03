package com.helixz.awsgitdemo.exception.impl;

public class ValidationFailedException extends RuntimeException{
    public ValidationFailedException(String message){
        super(message);
    }
}
