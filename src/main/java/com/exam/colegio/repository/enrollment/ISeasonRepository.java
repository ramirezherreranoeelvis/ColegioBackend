package com.exam.colegio.repository.enrollment;

import com.exam.colegio.model.enrollment.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISeasonRepository extends JpaRepository<Season, Integer> {

        Optional<Season> findByYear(String year);

        @Query(value = "CALL usp_findAllSeasonByStudent(:dniStudent)", nativeQuery = true)
        List<Season> findAllByStudent(@Param("dniStudent") String dni);

}
