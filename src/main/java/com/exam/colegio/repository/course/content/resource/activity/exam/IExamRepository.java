package com.exam.colegio.repository.course.content.resource.activity.exam;

import com.exam.colegio.model.course.content.resource.activity.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExamRepository extends JpaRepository<Exam, Integer> {
}
