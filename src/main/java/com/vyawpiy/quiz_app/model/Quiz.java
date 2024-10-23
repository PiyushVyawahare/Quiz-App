package com.vyawpiy.quiz_app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;

    @ManyToMany
    List<Question> questions;
}
