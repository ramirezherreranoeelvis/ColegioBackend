package com.exam.colegio.repository.enrollment;

import com.exam.colegio.model.enrollment.EnrollmentStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface IEnrollmentStudentRepository extends JpaRepository<EnrollmentStudent, Integer> {

        @Query(value = "CALL GetEnrollmentStudentByStudentAndEnrollment(:studentId, :enrollmentId)", nativeQuery = true)
        Optional<EnrollmentStudent> findByStudentAndEnrollment(@Param("studentId") int studentId,
                                                               @Param("enrollmentId") int enrollmentId);

}
