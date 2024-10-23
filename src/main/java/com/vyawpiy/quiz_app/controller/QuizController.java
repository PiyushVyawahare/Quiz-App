package com.vyawpiy.quiz_app.controller;

import com.vyawpiy.quiz_app.model.QuestionWrapper;
import com.vyawpiy.quiz_app.model.Response;
import com.vyawpiy.quiz_app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculateScore(id, responses);
    }
}
