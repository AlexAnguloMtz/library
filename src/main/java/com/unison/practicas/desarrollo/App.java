package com.unison.practicas.desarrollo;

import com.unison.practicas.desarrollo.gui.GUI;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main( String[] args ) {
        GUI.launch(GUI.class, args);
    }

}