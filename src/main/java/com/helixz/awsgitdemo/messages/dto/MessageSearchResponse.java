package com.helixz.awsgitdemo.messages.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageSearchResponse {
    public List<MessageCreateResponse> contents;
    private  PaginationData paginationData;
    @Data
    @Builder
    public static class PaginationData{
        private int totalElements;
        private int totalPages;
        private  int page;
        private int pageSize;
    }
}
