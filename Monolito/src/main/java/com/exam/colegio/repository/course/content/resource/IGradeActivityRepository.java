package com.exam.colegio.repository.course.content.resource;

import com.exam.colegio.model.course.content.resource.GradeActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGradeActivityRepository extends JpaRepository<GradeActivity, Integer> {

}
