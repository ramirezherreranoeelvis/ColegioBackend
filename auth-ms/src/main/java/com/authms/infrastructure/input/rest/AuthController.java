package com.authms.infrastructure.input.rest;

import com.authms.infrastructure.input.rest.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
class AuthController {

      @PostMapping("/login")
      public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
            return ResponseEntity.ok("api funcional");
      }

}
