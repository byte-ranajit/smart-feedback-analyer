package com.example.feedbackai.service;

import java.util.List;
import java.util.Random;

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
        FeedbackAnalysis analysis = mapper.readValue(json, FeedbackAnalysis.class);
        if (analysis.getAutoResponser() == null || analysis.getAutoResponser().isBlank()) {
            analysis.setAutoResponser(fallbackResponse(analysis.getSentiment()));
        }
        return analysis;
    }

    private String fallbackResponse(String sentiment) {

        Random random = new Random();

        if ("Negative".equalsIgnoreCase(sentiment)) {

            List<String> apologies = List.of(
                    "We sincerely apologize for the distress this has caused.",
                    "We are very sorry to hear about this experience.",
                    "Please accept our heartfelt apologies for the inconvenience.",
                    "We truly regret the situation you are facing.");

            List<String> assurances = List.of(
                    "Your message is very important to us and will be reviewed urgently.",
                    "Our team is already looking into this matter with priority.",
                    "We take this seriously and will ensure it receives immediate attention.",
                    "This concern has been noted and will be addressed promptly.");

            List<String> supportLines = List.of(
                    "We are here to support you and will assist you as quickly as possible.",
                    "Please be assured that we will provide the necessary support.",
                    "Our goal is to help resolve this for you without delay.");

            return String.format(
                    "%s %s %s",
                    randomPick(apologies, random),
                    randomPick(assurances, random),
                    randomPick(supportLines, random));
        }

        if ("Positive".equalsIgnoreCase(sentiment)) {

            List<String> thanks = List.of(
                    "Thank you for taking the time to share your feedback.",
                    "We truly appreciate you reaching out to us.",
                    "Thank you for letting us know about your experience.");

            List<String> appreciation = List.of(
                    "Your feedback motivates us to keep improving.",
                    "We are glad to hear from you.",
                    "It means a lot to us.");

            return randomPick(thanks, random) + " " + randomPick(appreciation, random);
        }

        // Neutral
        List<String> neutralResponses = List.of(
                "Thank you for sharing your feedback with us. We will carefully review it.",
                "We appreciate you reaching out and will look into this matter.",
                "Your message has been received, and our team will review it shortly.");

        return randomPick(neutralResponses, random);
    }

    private String randomPick(List<String> list, Random random) {
        return list.get(random.nextInt(list.size()));
    }

}
