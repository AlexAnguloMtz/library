package com.unison.practicas.desarrollo.gui;

import com.unison.practicas.desarrollo.App;
import com.unison.practicas.desarrollo.gui.client.ClientsContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ConfigurableApplicationContext springContext = SpringApplication.run(App.class);

        ClientsContainer clientsContainer = springContext.getBean(ClientsContainer.class);

        WebViewAdapter webViewAdapter = new WebViewAdapter(new WebView());

        webViewAdapter.load("/templates/index.html");

        setDataClients(webViewAdapter, clientsContainer);

        StackPane root = new StackPane(webViewAdapter.getWebView());
        Scene scene = new Scene(root);
        stage.setScene(scene);

        setWindowDetails(stage);

        stage.show();
    }

    private void setDataClients(WebViewAdapter webViewAdapter, ClientsContainer clientsContainer) {
        webViewAdapter.setWindowMember("userClient", clientsContainer.getUserClient());
    }

    private void setWindowDetails(Stage stage) {
        stage.setTitle("Biblioteca");
        stage.setMaximized(true);
        stage.setResizable(true);
    }

}
