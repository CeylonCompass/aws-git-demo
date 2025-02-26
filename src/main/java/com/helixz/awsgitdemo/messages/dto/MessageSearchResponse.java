package com.helixz.awsgitdemo.messages.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Data
public class MessageSearchResponse {
    private List<MessageCreateResponse> contents;
    private PaginationData paginationData;

    @Data
    public static class PaginationData {
        private int totalElements;
        private int totalPages;
        private int page;
        private int pageSize;
    }
}

