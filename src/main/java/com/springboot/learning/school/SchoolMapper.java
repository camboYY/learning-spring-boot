package com.springboot.learning.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public SchoolResponseDto toSchoolResponseDto (School school) {
        return new SchoolResponseDto(school.getName());
    }

    public School toSchool (SchoolRequestDto dto) {
        return new School(dto.name());
    }
}
