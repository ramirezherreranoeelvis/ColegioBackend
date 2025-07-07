package com.exam.colegio.controller.person;

import com.exam.colegio.dao.person.IPersonDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gatomontes
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AuthController {

      @PostMapping
      public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {

            if (username.isBlank()) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            var personDTO = personDAO.findByUsername(username, password);

            if (personDTO.isEmpty()) {
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The specified username does not exist.");
            }
            logger.info(personDTO.get().getTypePerson());
            return ResponseEntity.ok(personDTO);

      }

      private java.util.logging.Logger logger = java.util.logging.Logger.getLogger(getClass().getName());
      private final IPersonDAO personDAO;

      public AuthController(IPersonDAO personDAO) {
            this.personDAO = personDAO;
      }

}



