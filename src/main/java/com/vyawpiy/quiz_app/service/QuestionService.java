package com.vyawpiy.quiz_app.service;

import com.vyawpiy.quiz_app.model.Question;
import com.vyawpiy.quiz_app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try {
            return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            if(questionRepository.existsById(id)) {
                questionRepository.deleteById(id);
                Question question = questionRepository.findById(id).orElse(new Question());
                if (question.getId() == id) return new ResponseEntity<>("Not Deleted", HttpStatus.NOT_IMPLEMENTED);
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            }
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error in deleting", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Question> updateQuestion(int id, Question question) {
        try {
            question.setId(id);
            Question question1 = questionRepository.save(question);
            return new ResponseEntity<>(question1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(), HttpStatus.NOT_FOUND);
    }
}
