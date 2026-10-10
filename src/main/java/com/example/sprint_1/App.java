package com.example.sprint_1;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;



public class App {

    Scene scene;
    Button button = new Button();

    public App() {buildScene();}

    public void buildScene() {
        Text text = new Text("");
        text.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 20));
        text.setFill(Color.AQUA);
        TextFlow area = new TextFlow();
        area.getChildren().add(text);

        button.setText("Click Me");
        button.setOnAction(e -> {
            if (text.getText().isBlank())
                text.setText("Button Works!\tClick to reset.");
            else
                text.setText("");
        });

        VBox box = new VBox(10, area, button);
        this.scene = new Scene(box, 800, 600);

    }

    public Scene getScene() {return this.scene; };


}
