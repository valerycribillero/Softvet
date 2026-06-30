package com.exam.exam_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.exam_service.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    
}
