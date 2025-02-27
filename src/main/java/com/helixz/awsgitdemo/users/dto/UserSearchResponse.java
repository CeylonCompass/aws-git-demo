package com.helixz.awsgitdemo.users.dto;

import com.helixz.awsgitdemo.messages.dto.MessageSearchResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserSearchResponse {

    private List<UserCreateResponse> contents;

    private UserSearchResponse.PaginationData paginationData;

    @Data
    @Builder
    public static class PaginationData{
        private int totalElements;
        private int totalPages;
        private int page;
        private int pageSize;
    }


}
