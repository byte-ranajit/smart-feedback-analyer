package com.example.feedbackai.service;

import com.example.feedbackai.dto.FeedbackRequest;
import com.example.feedbackai.model.Feedback;
import com.example.feedbackai.model.FeedbackAnalysis;
import com.example.feedbackai.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository repository;
    private final LlmService llmService;

    public Feedback process(FeedbackRequest request) throws Exception {

        FeedbackAnalysis analysis = llmService.analyze(request.getMessage());
        Feedback feedback = new Feedback();
        feedback.setUserName(request.getUserName());
        feedback.setMessage(request.getMessage());
        feedback.setSentiment(analysis.getSentiment());
        feedback.setPriority(analysis.getPriority());
        feedback.setCategory(analysis.getCategory());
        feedback.setAutoResponser(analysis.getAutoResponser());

        return repository.save(feedback);
    }
}
