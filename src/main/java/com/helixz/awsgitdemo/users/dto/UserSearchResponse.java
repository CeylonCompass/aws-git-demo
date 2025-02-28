package com.helixz.awsgitdemo.users.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserSearchResponse {

    List<UserCreateResponse> usersList;

    PaginationData paginationData;

    @Data
    @Builder
    public static class PaginationData{
        private int totalElements;
        private int totalPages;
        private int page;
        private int pageSize;
    }
}
