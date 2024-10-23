package com.vyawpiy.quiz_app.controller;

import com.vyawpiy.quiz_app.model.Question;
import com.vyawpiy.quiz_app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

}
