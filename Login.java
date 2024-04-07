package com.example.CompSCIHLIA;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login {
    private Stage stage;
    private Map<String, String> userCredentials = new HashMap<>();

    public Login(Stage stage) {
        this.stage = stage;
    }

    public void display() {
        readLoginDetails();

        VBox loginLayout = new VBox(10);
        loginLayout.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            if (authenticate(usernameField.getText(), passwordField.getText())) {
                Quiz quiz = new Quiz(stage);
                quiz.display();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid credentials.");
                alert.show();
            }
        });

        loginLayout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

        Scene scene = new Scene(loginLayout, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Quiz Application Login");
        stage.show();
    }

    private boolean authenticate(String username, String password) {
        return "user".equals(username) && "user".equals(password) ||
                userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    private void readLoginDetails() {
        String filePath = "/Users/jasur/Desktop/Internal Assesment/Computer Science/Source/LoginDetails.txt"; //setting the text file path
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) { //introducing the BufferedReader and setting its direction to the path
            String line;
            while ((line = reader.readLine()) != null) { //while line is not empty
                String[] details = line.split(","); //understand where username and password are, since they are split with a comma
                userCredentials.put(details[0], details[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
