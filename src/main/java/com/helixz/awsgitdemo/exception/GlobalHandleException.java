package com.helixz.awsgitdemo.exception;

import com.helixz.awsgitdemo.exception.impl.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<GlobalHandleExceptionDTO> errorException (ValidationFailedException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalHandleExceptionDTO.builder().status("Failed").message(ex.getMessage()).build());
    }

}
