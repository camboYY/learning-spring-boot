package com.springboot.learning.student;

import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService service;

    @Mock
    private StudentMapper mapper;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSuccessfullySaveStudent() {
        //Given
        StudentRequestDto studentRequestDto = new StudentRequestDto(12,"Yous", "Yoeun", "yous@hotmail.com",12);
        Student student = new Student();
        student.setAge(12);
        student.setEmail("yous@hotmail.com");
        student.setFirstName("Yous");
        student.setLastName("Yoeun");

        //Mock call to dependencies
        Mockito.when(mapper.toStudent(studentRequestDto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Mockito.when(mapper.toStudentResponseDto(student)).thenReturn(new StudentResponseDto("Yous", "Yoeun", "yous@hotmail.com",12));
        // call saveStudent func
        StudentResponseDto studentResponseDto = service.saveStudent(studentRequestDto);
        // Assertion
        assertEquals(studentRequestDto.firstName(), studentResponseDto.firstName());
        assertEquals(studentRequestDto.lastName(), studentResponseDto.lastName());
        assertEquals(studentRequestDto.email(), studentResponseDto.email());
        assertEquals(studentRequestDto.age(), studentResponseDto.age());

        // Ensure that each func called only one time
        Mockito.verify(mapper,Mockito.times(1)).toStudent(studentRequestDto);
        Mockito.verify(studentRepository,Mockito.times(1)).save(student);
        Mockito.verify(mapper,Mockito.times(1)).toStudentResponseDto(student);

    }

    @Test
    void deleteStudent() {
    }

    @Test
    void getStudentsByFirstnameOrLastname() {
        //Given
        List<Student> list = new ArrayList<>();
        list.add(new Student("Yous", "Yoeun", "yous@hotmail.com",12));
        list.add(new Student("Bro", "Jolwer", "jol@hotmail.com",20));
        list.add(new Student("YCool", "Kpl", "kpl@hotmail.com",12));
        String firstName = "Y";

        // Mock dependencies
        Mockito.when(studentRepository.findAllByFirstNameContaining(firstName)).thenReturn(list);
        Mockito.when(mapper.toStudentResponseDto(Mockito.any(Student.class))).thenReturn(new StudentResponseDto("Yous", "Yoeun", "yous@hotmail.com",12));
        List<StudentResponseDto> studentResponseDtos = service.getStudentsByFirstname(firstName);
        assertEquals(list.size(),studentResponseDtos.size());
        //verify call one time only.
        Mockito.verify(studentRepository,Mockito.times(1)).findAllByFirstNameContaining(firstName);
    }

    @Test
    void findStudentById() {
    }
}