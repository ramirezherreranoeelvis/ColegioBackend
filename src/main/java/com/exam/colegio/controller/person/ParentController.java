package com.exam.colegio.controller.person;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.colegio.dao.enrollment.IEnrollmentDAO;
import com.exam.colegio.dao.person.IFatherDAO;
import com.exam.colegio.dao.person.IMotherDAO;
import com.exam.colegio.dao.person.IPersonDAO;
import com.exam.colegio.dto.HijoStudentDTO;
import com.exam.colegio.dto.MatriculaRegistrarDTO;
import com.exam.colegio.model.person.Father;
import com.exam.colegio.model.person.Mother;
import com.exam.colegio.model.person.Student;

@RestController
@RequestMapping("/parent")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ParentController {

        @GetMapping("/students")
        public ResponseEntity<?> getStudents(@RequestParam String dniParent) {
                Optional<String> typeParentOptional = personDAO.getTypeParent(dniParent);
                if (typeParentOptional.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent not found");
                }

                String typeParent = typeParentOptional.get();
                List<Student> listStudents;

                if (typeParent.equals("father")) {
                        Optional<Father> fatherOptional = fatherDAO.findByDni(dniParent);
                        if (fatherOptional.isEmpty()) {
                                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Father not found");
                        }
                        listStudents = fatherOptional.get().getStudents();
                } else {
                        Optional<Mother> motherOptional = motherDAO.findByDni(dniParent);
                        if (motherOptional.isEmpty()) {
                                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mother not found");
                        }
                        listStudents = motherOptional.get().getStudents();
                }

                if (listStudents.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No children found");
                }


                return ResponseEntity.ok(listStudents.stream().map(student -> {
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                        var enrollmentOptional = this.enrollmentDAO.findByGrade(student.getGrade().getNextGrade());
                        MatriculaRegistrarDTO enrollmentDTO = null;
                        if (enrollmentOptional.isPresent()) {
                                var enrollment = enrollmentOptional.get();
                                enrollmentDTO = MatriculaRegistrarDTO.builder()
                                                .idEnrollment(enrollment.getIdEnrollment())
                                                .startDate(formato.format(enrollment.getSeason().getStartDate()))
                                                .nameGrade(enrollment.getGrade().getName())
                                                .vacancies(enrollment.getVacancies()).enrolled(enrollment.getEnrolled())
                                                .cost(enrollment.getCost()).monthlyFee(enrollment.getMonthlyFee())
                                                .build();
                        }
                        return HijoStudentDTO.builder().dni(student.getDni()).name(student.getName())
                                        .surnamePaternal(student.getSurnamePaternal())
                                        .surnameMaternal(student.getSurnameMaternal())
                                        .phoneNumber(student.getPhoneNumber())
                                        .accessEnabled(student.getAccess().isAccessEnabled())
                                        .username(student.getAccess().getUsername())
                                        .password(student.getAccess().getPassword())
                                        .description(student.getAccess().getDescription())
                                        .grade(student.getGrade().getName()).nextEnrollment(enrollmentDTO).build();
                }).toList());
        }


        private final IEnrollmentDAO enrollmentDAO;
        private final IPersonDAO personDAO;
        private final IFatherDAO fatherDAO;
        private final IMotherDAO motherDAO;

        public ParentController(IEnrollmentDAO enrollmentDAO, IPersonDAO personDAO, IFatherDAO fatherDAO, IMotherDAO motherDAO) {
                this.enrollmentDAO = enrollmentDAO;
                this.personDAO = personDAO;
                this.fatherDAO = fatherDAO;
                this.motherDAO = motherDAO;
        }

}