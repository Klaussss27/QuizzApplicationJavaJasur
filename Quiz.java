package com.example.CompSCIHLIA;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

public class Quiz {
    private Stage stage;
    private Map<String, List<String[]>> questionsMap;

    public Quiz(Stage stage) {
        this.stage = stage;
        this.questionsMap = QuestionLoader.loadQuestions("/Users/jasur/Desktop/Internal Assesment/Computer Science/Source/QuestionsAnswers.txt");
    }

    public void display() {
        VBox quizLayout = new VBox(10);
        quizLayout.setAlignment(Pos.CENTER);

        Button topic1Button = new Button("Topic 1: Introduction to Economics");
        topic1Button.setOnAction(e -> loadTopicQuestions("Topic 1"));

        Button topic2Button = new Button("Topic 2: Microeconomics");
        topic2Button.setOnAction(e -> loadTopicQuestions("Topic 2"));

        Button topic3Button = new Button("Topic 3: Macroeconomics");
        topic3Button.setOnAction(e -> loadTopicQuestions("Topic 3"));

        Button topic4Button = new Button("Topic 4: The Global Economy");
        topic4Button.setOnAction(e -> loadTopicQuestions("Topic 4"));

        quizLayout.getChildren().addAll(topic1Button, topic2Button, topic3Button, topic4Button);

        Scene scene = new Scene(quizLayout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Quiz Topics");
        stage.show();
    }

    private void loadTopicQuestions(String topic) {
        List<String[]> topicQuestions = questionsMap.getOrDefault(topic, new ArrayList<>());
        Collections.shuffle(topicQuestions);
        showQuestion(topicQuestions, 0, 0);
    }

    private void showQuestion(List<String[]> questions, int questionIndex, int score) {
        if (questionIndex >= questions.size()) {
            Alert scoreAlert = new Alert(Alert.AlertType.INFORMATION, "You scored " + score + " out of " + questions.size());
            scoreAlert.setHeaderText("Quiz Completed!");
            scoreAlert.showAndWait();
            display(); // Return to the topic selection screen
            return;
        }

        String[] currentQuestion = questions.get(questionIndex);
        String questionText = currentQuestion[0];
        String correctAnswer = currentQuestion[1];

        VBox questionLayout = new VBox(10);
        questionLayout.setAlignment(Pos.CENTER);

        Label questionLabel = new Label("Q: " + questionText);
        questionLayout.getChildren().add(questionLabel);

        List<String> allAnswers = new ArrayList<>();
        allAnswers.add(correctAnswer); // Add the correct answer

        Set<String> usedAnswers = new HashSet<>(Collections.singletonList(correctAnswer));
        Random rand = new Random();
        while (allAnswers.size() < 4) {
            String[] otherQuestion = questions.get(rand.nextInt(questions.size()));
            String otherAnswer = otherQuestion[1];
            if (!usedAnswers.contains(otherAnswer)) {
                allAnswers.add(otherAnswer);
                usedAnswers.add(otherAnswer);
            }
        }

        Collections.shuffle(allAnswers);

        for (String answer : allAnswers) {
            Button answerButton = new Button(answer);
            answerButton.setOnAction(e -> {
                int updatedScore = correctAnswer.equals(answer) ? score + 1 : score;
                showQuestion(questions, questionIndex + 1, updatedScore);
            });
            questionLayout.getChildren().add(answerButton);
        }

        Scene questionScene = new Scene(questionLayout, 400, 300);
        stage.setScene(questionScene);
        stage.show();
    }
}
