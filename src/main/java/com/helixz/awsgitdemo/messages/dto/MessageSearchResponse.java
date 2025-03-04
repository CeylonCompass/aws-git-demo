package com.helixz.awsgitdemo.messages.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * @author Chamith Kodikara
 */
@Data
@Builder
public class MessageSearchResponse {
    private List<MessageCreateResponse> contents;
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
