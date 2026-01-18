package com.example.feedbackai.dto;

import lombok.Data;

@Data
public class FeedbackResponse {
    private String sentiment;
    private String priority;
    private String category;
    private String autoResponse;
}
