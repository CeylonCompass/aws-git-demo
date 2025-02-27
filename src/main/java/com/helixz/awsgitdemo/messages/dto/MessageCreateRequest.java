package com.helixz.awsgitdemo.messages.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


public record MessageCreateRequest(

        @NotBlank(message = "message invalid")
        String message
) {


}
