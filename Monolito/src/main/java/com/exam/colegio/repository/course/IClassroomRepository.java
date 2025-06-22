package com.exam.colegio.repository.course;

import com.exam.colegio.model.course.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassroomRepository extends JpaRepository<Classroom, Integer> {

}
