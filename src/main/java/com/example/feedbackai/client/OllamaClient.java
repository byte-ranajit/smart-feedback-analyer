package com.example.feedbackai.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Component
public class OllamaClient {

    private static final String OLLAMA_URL =
            "http://localhost:11434/api/generate";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public String analyze(String feedback) throws Exception {

        String prompt = """
        Analyze the following feedback.
        Return ONLY valid JSON:
        {
          "sentiment": "Positive|Neutral|Negative",
          "priority": "LOW|MEDIUM|HIGH",
          "category": "Cleanliness|Staff|Service|Other",
          "autoResponse": "string"
        }

        Feedback: "%s"
        """.formatted(feedback);

        Map<String, Object> request = Map.of(
                "model", "mistral",
                "prompt", prompt,
                "stream", false
        );

        String response =
                restTemplate.postForObject(
                        OLLAMA_URL,
                        request,
                        String.class
                );

        Map result = mapper.readValue(response, Map.class);
        return result.get("response").toString();
    }
}
