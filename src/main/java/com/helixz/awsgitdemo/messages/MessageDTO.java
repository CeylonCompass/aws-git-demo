package com.helixz.awsgitdemo.messages;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class MessageDTO {

    private Long id;

    private String message;

    private LocalDateTime createdDate;

}
