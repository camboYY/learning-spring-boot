package com.springboot.learning.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    @GetMapping
    public List<SchoolResponseDto> getSchools() {
        return this.service.getSchools();
    }

    @PostMapping
    public SchoolResponseDto saveSchool (@RequestBody SchoolRequestDto dto) {
        return this.service.saveSchool(dto);
    }
}
