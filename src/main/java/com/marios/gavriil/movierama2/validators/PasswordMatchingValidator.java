package com.marios.gavriil.movierama2.validators;

import com.marios.gavriil.movierama2.annotations.PasswordMatching;
import com.marios.gavriil.movierama2.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {

    @Override
    public void initialize(PasswordMatching constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserDto userDto = (UserDto) value;

        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}
