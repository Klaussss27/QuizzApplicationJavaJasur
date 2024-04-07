package com.example.CompSCIHLIA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionLoader {
    public static Map<String, List<String[]>> loadQuestions(String filePath) {
        Map<String, List<String[]>> questionsMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                String topicKey = "Topic " + parts[0].trim();
                String question = parts[1].trim();
                String answer = parts[2].trim();
                questionsMap.computeIfAbsent(topicKey, k -> new ArrayList<>()).add(new String[]{question, answer});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionsMap;
    }
}
