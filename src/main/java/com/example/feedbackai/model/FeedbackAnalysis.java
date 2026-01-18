package com.example.feedbackai.model;

import lombok.Data;

@Data
public class FeedbackAnalysis {
    private String sentiment;
    private String priority;
    private String category;
    private String autoResponser;
}
