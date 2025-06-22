package com.exam.colegio.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DniValidator implements ConstraintValidator<Dni, Integer> {

        @Override
        public void initialize(Dni constraintAnnotation) {
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
                if (value == null) {
                        return false; // o true dependiendo de si consideras válido el valor nulo
                }
                String dniString = String.valueOf(value);
                return dniString.length() == 8;
        }

}
