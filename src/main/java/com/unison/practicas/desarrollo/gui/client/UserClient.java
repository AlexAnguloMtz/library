package com.unison.practicas.desarrollo.gui.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unison.practicas.desarrollo.core.util.PaginationRequest;
import com.unison.practicas.desarrollo.core.dto.UserPreviewsQuery;
import com.unison.practicas.desarrollo.core.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserClient {

    private final ObjectMapper objectMapper;
    private final UserService userService;

    public UserClient(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    public String getUsersPreviews(String queryJson, String paginationJson) throws JsonProcessingException {
        var query = objectMapper.readValue(queryJson, UserPreviewsQuery.class);
        var pagination = objectMapper.readValue(paginationJson, PaginationRequest.class);
        var items = userService.getUsersPreviews(query, pagination);
        return objectMapper.writeValueAsString(items);
    }

}