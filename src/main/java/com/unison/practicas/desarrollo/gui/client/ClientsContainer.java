package com.unison.practicas.desarrollo.gui.client;

import org.springframework.stereotype.Component;

@Component
public class ClientsContainer {

    private final UserClient userClient;

    public ClientsContainer(UserClient userClient) {
        this.userClient = userClient;
    }

    public UserClient getUserClient() {
        return userClient;
    }

}