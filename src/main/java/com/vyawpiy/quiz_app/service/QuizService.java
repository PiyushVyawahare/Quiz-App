package com.vyawpiy.quiz_app.service;

import com.vyawpiy.quiz_app.model.Question;
import com.vyawpiy.quiz_app.model.QuestionWrapper;
import com.vyawpiy.quiz_app.model.Quiz;
import com.vyawpiy.quiz_app.model.Response;
import com.vyawpiy.quiz_app.repository.QuestionRepository;
import com.vyawpiy.quiz_app.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {

        Optional<Quiz> quiz = quizRepository.findById(id);

        if(quiz.isEmpty()) return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);

        List<Question> questionsFromDB = quiz.get().getQuestions();

        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q: questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();

        List<Question> questions = quiz.getQuestions();

        int score = 0, i = 0;

        for(Question q:questions) {
            for(Response r: responses) {
                if(q.getId() == r.getId() && q.getRightAnswer().equals(r.getResponse())) {
                    score++;
                }
            }
            i++;
        }

        return new ResponseEntity<>(score, HttpStatus.OK);

    }
}
