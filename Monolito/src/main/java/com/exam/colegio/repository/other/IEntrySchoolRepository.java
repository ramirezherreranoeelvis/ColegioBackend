package com.exam.colegio.repository.other;

import com.exam.colegio.model.other.EntrySchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IEntrySchoolRepository extends JpaRepository<EntrySchool, Integer> {

        @Query(value = "CALL findAllByStudent(:dniStudent)", nativeQuery = true)
        List<EntrySchool> findAllByStudent(@Param("dniStudent") String dniStudent);

}
