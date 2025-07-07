package com.exam.colegio.controller.workspace;

import com.exam.colegio.dao.other.IEntrySchoolDAO;
import com.exam.colegio.dao.person.IStudentDAO;
import com.exam.colegio.dto.historial.SemanaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/historial")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class HistorialDeIngresoController {

        @GetMapping
        public ResponseEntity<List<SemanaDTO>> historial(@RequestParam String dniStudent) {
                var student = this.studentDAO.findByDni(dniStudent);
                return ResponseEntity.ok(this.entrySchoolDAO.finAllSemanaDTOByStudent(student.get()));
        }

        private final IEntrySchoolDAO entrySchoolDAO;
        private final IStudentDAO studentDAO;

        public HistorialDeIngresoController(IEntrySchoolDAO entrySchoolDAO, IStudentDAO studentDAO) {
                this.entrySchoolDAO = entrySchoolDAO;
                this.studentDAO = studentDAO;
        }

}
