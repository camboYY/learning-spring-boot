package com.springboot.learning.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(SchoolService.class);

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public List<SchoolResponseDto> getSchools () {
        return schoolRepository.findAll().stream().map(schoolMapper::toSchoolResponseDto).collect(Collectors.toList());
    }

    public SchoolResponseDto saveSchool(SchoolRequestDto dto) {
        LOGGER.info(String.format("SchoolRequestDto: %s" , dto.toString()));

        School school = this.schoolMapper.toSchool(dto);
        LOGGER.info(String.format("School: %s" , school.toString()));

        School savedSchool = schoolRepository.save(school);
        LOGGER.info(String.format("savedSchool: %s" , savedSchool.toString()));

        return this.schoolMapper.toSchoolResponseDto(savedSchool);
    }
}
