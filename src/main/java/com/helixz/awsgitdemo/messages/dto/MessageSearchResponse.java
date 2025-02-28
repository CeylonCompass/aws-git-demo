package com.helixz.awsgitdemo.messages.dto;

import com.helixz.awsgitdemo.messages.MessageDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageSearchResponse {
    private List<MessageDTO> contents;
    private PaginationData paginationData;

    @Data
    @Builder
    public static class PaginationData {
        private int totalElements;
        private int totalPages;
        private int page;
        private int pageSize;
    }
}
