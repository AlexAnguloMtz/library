package com.unison.practicas.desarrollo.gui;

import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.net.URL;

public class WebViewAdapter {

    private final WebView webView;

    public WebViewAdapter(WebView webView) {
        this.webView = webView;
    }

    // Add an object to the Javascript window of the WebView
    void setWindowMember(String key, Object object) {
        webView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webView.getEngine().executeScript("window");
                window.setMember(key, object);
            }
        });
    }

    public void load(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            throw new RuntimeException("Could not load resource %s".formatted(path));
        }
        webView.getEngine().load(url.toExternalForm());
    }

    public WebView getWebView() {
        return webView;
    }
}