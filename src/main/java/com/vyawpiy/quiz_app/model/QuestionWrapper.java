package com.vyawpiy.quiz_app.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWrapper {

    private int id;
    private String title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
