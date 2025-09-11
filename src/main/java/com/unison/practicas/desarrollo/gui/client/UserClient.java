package com.unison.practicas.desarrollo.gui.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unison.practicas.desarrollo.core.service.UserService;

public class UserClient {

    private final ObjectMapper objectMapper;
    private final UserService userService;

    public UserClient(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    public String getUsers() throws JsonProcessingException {
        return objectMapper.writeValueAsString(userService.getUsers());
    }

}
