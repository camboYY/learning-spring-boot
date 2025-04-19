package com.springboot.learning.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        this.studentMapper = new StudentMapper();
    }

    @Test
    void shouldMapStudentRequestDtoToStudent() {
        System.out.println("Test method");
        StudentRequestDto studentRequestDto = new StudentRequestDto(12,"Yous", "Yoeun", "yous@hotmail.com",12);
       Student student = studentMapper.toStudent(studentRequestDto);
        assertEquals(studentRequestDto.firstName(), student.getFirstName());
        assertEquals(studentRequestDto.lastName(), student.getLastName());
        assertEquals(studentRequestDto.email(), student.getEmail());
        assertEquals(studentRequestDto.age(), student.getAge());
        assertNotNull(studentRequestDto.schoolId());
        assertNotNull(student.getSchool());
    }

    @Test
    void shouldMapStudentToStudentResponseDto() {
        Student student = new Student();
        student.setAge(12);
        student.setEmail("yous@hotmail.com");
        student.setFirstName("joker");
        student.setLastName("Vikol");
        StudentResponseDto dto = studentMapper.toStudentResponseDto(student);
        assertEquals(student.getAge(), dto.age());
        assertEquals(student.getFirstName(), dto.firstName());
        assertEquals(student.getLastName(), dto.lastName());
        assertEquals(student.getEmail(), dto.email());
        assertNull(student.getSchool());
    }

    @Test()
    void should_thow_null_pointer_exception_when_student_request_dto_is_null() {
        NullPointerException exp = assertThrows(NullPointerException.class,()->studentMapper.toStudent(null));
        assertEquals("Student Dto should not be null",exp.getMessage());
    }
}