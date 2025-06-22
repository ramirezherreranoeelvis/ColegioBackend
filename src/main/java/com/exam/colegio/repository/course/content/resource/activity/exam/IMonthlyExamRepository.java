package com.exam.colegio.repository.course.content.resource.activity.exam;

import com.exam.colegio.model.course.content.resource.activity.exam.MonthlyExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMonthlyExamRepository extends JpaRepository<MonthlyExam, Integer> {
}