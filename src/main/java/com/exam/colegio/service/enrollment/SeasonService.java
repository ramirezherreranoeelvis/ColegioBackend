package com.exam.colegio.service.enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exam.colegio.dao.enrollment.ISeasonDAO;
import com.exam.colegio.dto.TemporadaDTO;
import com.exam.colegio.model.enrollment.Season;
import com.exam.colegio.model.person.Student;
import com.exam.colegio.repository.enrollment.ISeasonRepository;
@Service
public class SeasonService implements ISeasonDAO {

        @Override
        public Optional<Season> findByYear(String year) {
                return this.seasonRepository.findByYear(year);
        }

        @Override
        public List<TemporadaDTO> findByStudent(Student student) {
                return this.seasonRepository.findAllByStudent(student.getDni()).stream().map(season -> TemporadaDTO.builder().year(season.getYear()).startDate(season.getStartDate()).build()).toList();
        }

        private ISeasonRepository seasonRepository;

        public SeasonService(ISeasonRepository seasonRepository) {
                this.seasonRepository = seasonRepository;
        }

}
