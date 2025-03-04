package com.helixz.awsgitdemo.users.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSearchResponse {
    private List<UserCreateResponse> users;
    private UserSearchResponse.PaginationData paginationData;

    @Data
    @Builder
    public static class PaginationData {
        private int totalElements;
        private int totalPages;
        private int page;
        private int pageSize;
    }
}
