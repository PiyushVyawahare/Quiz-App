package com.vyawpiy.quiz_app.repository;

import com.vyawpiy.quiz_app.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
