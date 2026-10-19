package com.example.sprint_1;

/*
Edits:
Date        Name        Edits
====        ====        ========================================
10/17       Mo          Recreated login in using javafx
10/18       Mo          Created UI to view user info based on the selected user.
                        Added logout option and formatting/alignments of UI elements.
 */




import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;

import java.time.LocalDate;


public class App {

    Stage stage;        //Points to the primary stage

    Scene loginScene;
    Scene profileScene;

    User user;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    public App(Stage stage) {
        this.stage = stage;

        buildLoginScene();
    }

    //----------------Login Scene-------------------------------------------------------------------
    public void buildLoginScene() {
        //Create a box for the main pane, Hbox to show login options
        VBox vbox = new VBox(20);
        HBox hbox = new HBox(30);
        //Title of the login page
        Label titl = new Label("Select your Profile: ");
        titl.setAlignment(Pos.CENTER);
        titl.setMaxWidth(Double.MAX_VALUE);         //Stretch horizontally and center
        titl.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 35));
        titl.setTextFill(Color.BLACK);

        //Profile 1 button
        Button buttonp1 = new Button("Profile_1");
        buttonp1.setAlignment(Pos.CENTER);
        buttonp1.setPrefSize(300,100);
        buttonp1.setStyle("-fx-background-color: #1e1ebf;" + "-fx-text-fill: white;" + "-fx-font-size: 24px;" +
                "-fx-font-weight: 800;" + "-fx-padding: 15 25 15 25;" + "-fx-background-radius: 15px");

        buttonp1.setOnAction(e -> {
            alert.setTitle("Profile Selected");
            alert.setContentText("You picked Profile 1.");
            alert.setHeaderText(null);

            alert.showAndWait();

            user = preload(buttonp1.getText());        //Preload user data and logs
            setProfileScene();                          //Set up profile scene
            stage.setScene(profileScene);
        });

        //Profile 2 button
        Button buttonp2 = new Button("Profile_2");
        buttonp2.setAlignment(Pos.CENTER);
        buttonp2.setPrefSize(300,100);
        buttonp2.setStyle("-fx-background-color: #6e6edd;" + "-fx-text-fill: white;" + "-fx-font-size: 24px;" +
                "-fx-font-weight: 800;" + "-fx-padding: 15 25 15 25;" + "-fx-background-radius: 15px");

        buttonp2.setOnAction(e -> {
            alert.setTitle("Profile Selected");
            alert.setContentText("You picked Profile 2.");
            alert.setHeaderText(null);
            alert.showAndWait();

            user = preload(buttonp2.getText());        //Preload user data and logs
            setProfileScene();                          //Set up profile scene
            stage.setScene(profileScene);
            System.out.println("4. Scene switched!"); // Check if this prints
        });

        hbox.getChildren().addAll(buttonp1,buttonp2);       //Add the profile buttons
        HBox.setHgrow(buttonp1, Priority.ALWAYS);               //Expand them to fill width of the screen
        HBox.setHgrow(buttonp2, Priority.ALWAYS);
        buttonp1.setMaxWidth(Double.MAX_VALUE);
        buttonp2.setMaxWidth(Double.MAX_VALUE);

        vbox.getChildren().addAll(titl,hbox);               //Add title, and hbox to vbox


        this.loginScene = new Scene(vbox, 800, 600);        //Add vbox to login scene
        stage.setScene(loginScene);                                      //Add scene to stage

    }



//----------------Login Scene-------------------------------------------------------------------

    public void setProfileScene() {
        BorderPane mainpane = new BorderPane();             //Borderpane as the mainpane
        //Creat top bar
        HBox tbar = new HBox(10);
        tbar.setPrefHeight(40);
        tbar.setAlignment(Pos.CENTER_LEFT);

        Region spacer = new Region();                       //Spacer to keep options separated
        HBox.setHgrow(spacer, Priority.ALWAYS);             //Consume all leftover space
        //Set label = username
        Label profile = new Label("Profile: " + user.getUserName());
        profile.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 20));
        //Logout button
        Button logout = new Button("Logout");
        logout.setStyle("-fx-font-weight: bold;" + "-fx-background-radius: 10px;" +
                "-fx-border-radius: 10px;");
        logout.setOnAction(e -> { stage.setScene(loginScene);});      //Brings user back to login

        tbar.getChildren().addAll(profile, spacer, logout);         //Add the elements to the top bar
        tbar.setStyle("-fx-border-color: #746f6f;" + "-fx-border-width: 0 0 2 0;");
        mainpane.setTop(tbar);              //Add elements to main pane

        //----Create Splitpane to show profile data and logs
        SplitPane splitPane = new SplitPane();         //Profile | Logs

        //-----------------------------Left pane to show user info
        VBox leftBox = new VBox(10);
        leftBox.setPadding(new Insets(10));
        leftBox.setAlignment(Pos.TOP_LEFT);                     //Align top left

        Label t = new Label("User Info...");        //title
        t.setStyle("-fx-font-size: 18px;" + "-fx-font-family: 'Segoe UI', 'Arial';" +
                "-fx-font-weight: bold;");
        Label p = new Label(user.toString());                      //User info
        p.setStyle("-fx-font-size: 14px;" + "-fx-font-family: 'Segoe UI', 'Arial';" +
                "-fx-font-weight: normal;");
        leftBox.getChildren().addAll(t, p);                         //Add title and user data to left box
        VBox.setVgrow(leftBox, Priority.ALWAYS);                    //Fill vertically

        //------------------Right pane to show logs
        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(10));
        rightBox.setAlignment(Pos.TOP_LEFT);

        //Add Listview for logs
        Label logTitle = new Label("User Logs...");        //title for log screen
        logTitle.setStyle("-fx-font-size: 18px;" + "-fx-font-family: 'Segoe UI', 'Arial';" +
                "-fx-font-weight: bold;");

        ObservableList<DailyLog> obLogs = FXCollections.observableArrayList(user.getLogs());      //ArrayList -> Observeable list
        ListView<DailyLog> listLogs = new ListView<>(obLogs);               //Build listview using obLogs
        listLogs.setStyle("-fx-font-size: 12px;" + "-fx-font-family: 'Segoe UI', 'Arial';" +
                "-fx-font-weight: normal;");                    //Set styling for listview
        rightBox.getChildren().addAll(logTitle, listLogs);          //Add title and listview to right box
        VBox.setVgrow(listLogs, Priority.ALWAYS);                    //Fill vertically


        //--------Add to mainpane and scene
        splitPane.getItems().addAll(leftBox,rightBox);          //Add both vbox to splitpane
        splitPane.setDividerPositions(0.4);                     //Divide 40/60
        mainpane.setCenter(splitPane);                          //Add splitpane to center of mainpain
        this.profileScene = new Scene(mainpane, 1000, 800);        //Add pane to login scene
    }
    //--------------------------------Helper Methods----------------------------------------------
    public Scene getScene() {return this.loginScene; };

    //Preload data
    public User preload(String username){
        int p = Character.getNumericValue(username.charAt(username.length()-1));        //get the correct profile
        LocalDate date = LocalDate.of(2026,10,1);
        LocalDate date1 = date.plusDays(1);
        LocalDate date2 = date.plusDays(2);
        LocalDate date3 = date.plusDays(3);

        if (p == 1) {
            //Create user
            User p1 = new User(username, date, "John",
                    "Smith", 25, "Female", 68, 150, 130);

            //Create logs and add to user's profile
            DailyLog log1 = new DailyLog(p1, date1, 150, 5500, 2000,
                    DailyLog.Activity.CYCLING, 25);
            DailyLog log2 = new DailyLog(p1, date2, 150, 6000, 1900,
                    DailyLog.Activity.CYCLING, 25);
            DailyLog log3 = new DailyLog(p1, date3, 148, 6500, 1800,
                    DailyLog.Activity.CYCLING, 25);
            p1.addLog(log1); p1.addLog(log2); p1.addLog(log3);      //Add logs
            return p1;
        }
        else
        {
            //Create user
            User p2 = new User(username, date, "Jane",
                    "Smith", 25, "Female", 62, 120, 110);

            //Create logs and add to user's profile
            DailyLog log1 = new DailyLog(p2, date1, 120, 5500, 2000,
                    DailyLog.Activity.CYCLING, 25);
            DailyLog log2 = new DailyLog(p2, date2, 120, 6000, 1900,
                    DailyLog.Activity.CYCLING, 25);
            DailyLog log3 = new DailyLog(p2, date3, 119, 6500, 1800,
                    DailyLog.Activity.CYCLING, 25);
            p2.addLog(log1); p2.addLog(log2); p2.addLog(log3);      //Add logs
            return p2;
        }
    }


}