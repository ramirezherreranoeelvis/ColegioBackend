package com.exam.colegio.repository.course;

import com.exam.colegio.model.course.CourseScheduled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICourseScheduledRepository extends JpaRepository<CourseScheduled, Integer> {

        @Query(value = "CALL GetCoursesByStudentAndEnrollment(:dniStudent, :idEnrollment)", nativeQuery = true)
        List<CourseScheduled> findCoursesByStudentAndEnrollment(@Param("dniStudent") String dniStudent,
                        @Param("idEnrollment") Integer idEnrollment);

        @Query(value = "CALL usp_findCoursesByDniStudentByYear(:student, :year)", nativeQuery = true)
        List<CourseScheduled> obtenerPorStudentYPorTemporada(@Param("student") String student,
                        @Param("year") String season);

        Optional<CourseScheduled> findByCode(String code);

        @Query(value = "CALL usp_findCourseBySeason(:year, :dni)", nativeQuery = true)
        List<CourseScheduled> findByYearAndTeacher(@Param("year") String season, @Param("dni") String student);

}
