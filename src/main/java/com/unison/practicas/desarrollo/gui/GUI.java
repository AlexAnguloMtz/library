package com.unison.practicas.desarrollo.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unison.practicas.desarrollo.core.service.UserService;
import com.unison.practicas.desarrollo.gui.client.UserClient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        WebViewAdapter webViewAdapter = new WebViewAdapter(new WebView());

        webViewAdapter.load("/templates/index.html");

        webViewAdapter.setWindowMember("userClient", new UserClient(new ObjectMapper(), new UserService()));

        StackPane root = new StackPane(webViewAdapter.getWebView());
        Scene scene = new Scene(root);
        stage.setScene(scene);

        setWindowDetails(stage);

        stage.show();
    }

    private void setWindowDetails(Stage stage) {
        stage.setTitle("Biblioteca");
        stage.setMaximized(true);
        stage.setResizable(true);
    }

}
