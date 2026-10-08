/*
Changes:
Date         Edits
====        ==============================================
10/8        Created a simple demo to confirm javafx libraries are funcitonal.


*/


package com.example.sprint_1;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.io.IOException;


public class Main extends Application {

    App app = new App();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Stage Created");

        stage.setScene(app.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
