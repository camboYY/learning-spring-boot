package com.springboot.learning.student;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService  {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    public StudentService (StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent (StudentRequestDto student) {
        LOGGER.info(String.format("StudentRequestDto: %s" , student.toString()));
        Student newStudent = this.studentMapper.toStudent(student);
        LOGGER.info(String.format("newStudent: %s" ,newStudent));
        Student savedStudent =  this.studentRepository.save(newStudent);
        return this.studentMapper.toStudentResponseDto(savedStudent);
    }

    public void deleteStudent(int id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(new BadRequestException("Student is not found."));
        }
    }

    public List<StudentResponseDto> getStudentsByFirstname (String firstName) {
        LOGGER.info(String.format("First Name: %s" , firstName));
        return studentRepository.findAllByFirstNameContaining(firstName).stream().map(studentMapper::toStudentResponseDto).collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(int studentId) throws BadRequestException {
        LOGGER.info(String.format("studentId: %s" , studentId));
        try {
            return studentRepository.findById(studentId).map(studentMapper::toStudentResponseDto).orElseThrow();
        } catch (Exception e) {
            throw new BadRequestException("Student is not found");
        }
    }
}
