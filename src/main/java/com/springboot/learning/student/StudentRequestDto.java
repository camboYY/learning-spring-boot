package com.springboot.learning.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record StudentRequestDto(@Positive int schoolId,
                                @NotEmpty String firstName,
                                @NotEmpty String lastName,
                                @NotEmpty @Email String email,
                               @Positive int age) {
}
