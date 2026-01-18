package com.example.feedbackai.controller;

import com.example.feedbackai.dto.FeedbackRequest;
import com.example.feedbackai.model.Feedback;
import com.example.feedbackai.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService service;

    @PostMapping
    public ResponseEntity<Feedback> submit(
            @RequestBody FeedbackRequest request) throws Exception {
        return ResponseEntity.ok(service.process(request));
    }
}
