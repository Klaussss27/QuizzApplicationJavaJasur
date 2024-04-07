package com.example.CompSCIHLIA;

import javafx.application.Application;
import javafx.stage.Stage;

public class QuizApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Login login = new Login(primaryStage);
        login.display();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
