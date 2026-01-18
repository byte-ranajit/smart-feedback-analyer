package com.example.feedbackai.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Component
public class OllamaClient {

        private static final String OLLAMA_URL = "http://localhost:11434/api/generate";

        private final RestTemplate restTemplate = new RestTemplate();
        private final ObjectMapper mapper = new ObjectMapper();

        public String analyze(String feedback) throws Exception {

                String prompt = """
                                You are a professional customer support assistant.

                                Your task is to analyze customer feedback and ALWAYS generate
                                a polite, empathetic, and professional response.

                                IMPORTANT RULES:
                                - autoResponse is MANDATORY
                                - autoResponse must NEVER be empty
                                - If sentiment is Negative, you MUST apologize
                                - If message sounds urgent or distressing, respond calmly and supportively
                                - Keep autoResponse human, respectful, and reassuring
                                - 2 to 3 sentences only

                                Return ONLY valid JSON.
                                NO markdown.
                                NO explanation.

                                JSON format:
                                {
                                  "sentiment": "Positive | Neutral | Negative",
                                  "priority": "LOW | MEDIUM | HIGH",
                                  "category": "Service | Staff | Cleanliness | Other",
                                  "autoResponse": "string"
                                }

                                Customer feedback:
                                "%s"
                                """.formatted(feedback);

                Map<String, Object> request = Map.of(
                                "model", "mistral",
                                "prompt", prompt,
                                "stream", false);

                String response = restTemplate.postForObject(
                                OLLAMA_URL,
                                request,
                                String.class);

                Map result = mapper.readValue(response, Map.class);
                return result.get("response").toString();
        }
}
