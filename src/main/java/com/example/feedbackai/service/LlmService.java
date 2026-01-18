package com.example.feedbackai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.feedbackai.client.OllamaClient;
import com.example.feedbackai.model.FeedbackAnalysis;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class LlmService {

    @Autowired
    private final OllamaClient ollamaClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public FeedbackAnalysis analyze(String message) throws Exception {
        String json = ollamaClient.analyze(message);
        return mapper.readValue(json, FeedbackAnalysis.class);
    }
}
