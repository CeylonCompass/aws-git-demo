package com.helixz.awsgitdemo.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GlobalHandleExceptionDTO {
    String status ;
    String message;
}
