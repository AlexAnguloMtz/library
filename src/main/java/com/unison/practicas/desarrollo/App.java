package com.unison.practicas.desarrollo;

import com.unison.practicas.desarrollo.gui.GUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {

    private static ConfigurableApplicationContext context;

    public static void main( String[] args ) {
        context = SpringApplication.run(App.class);
        GUI.launch(GUI.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

}