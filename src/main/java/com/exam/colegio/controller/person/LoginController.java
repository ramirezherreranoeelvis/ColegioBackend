package com.exam.colegio.controller.person;

import com.exam.colegio.dao.person.IPersonDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gatomontes
 */

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LoginController {

        @GetMapping
        public ResponseEntity<?> ingresarSistema(@RequestParam String username, @RequestParam String password) {

                if (username.isBlank()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
                }

                var personDTOOptional = personDAO.findByUsername(username, password);

                if (personDTOOptional.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The specified username does not exist.");
                }
                logger.info(personDTOOptional.get().getTypePerson());
                return ResponseEntity.ok(personDTOOptional.get());

        }

        private java.util.logging.Logger logger = java.util.logging.Logger.getLogger(getClass().getName());
        private final IPersonDAO personDAO;

        public LoginController(IPersonDAO personDAO) {
                this.personDAO = personDAO;
        }

}



