package com.example.feedbackai.dto;

import lombok.Data;

@Data
public class FeedbackRequest {
    private String userName;
    private String message;
}
