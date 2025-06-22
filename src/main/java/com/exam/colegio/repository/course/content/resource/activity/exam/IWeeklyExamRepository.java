package com.exam.colegio.repository.course.content.resource.activity.exam;

import com.exam.colegio.model.course.content.resource.activity.exam.WeeklyExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWeeklyExamRepository extends JpaRepository<WeeklyExam, Integer> {
}
