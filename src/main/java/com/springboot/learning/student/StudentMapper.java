package com.springboot.learning.student;

import com.springboot.learning.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstName(),  student.getLastName(), student.getEmail(), student.getAge());
    }

    public Student toStudent (StudentRequestDto student) {
        if(student == null) {
            throw new NullPointerException("Student Dto should not be null");
        }
        Student saveStudent = new Student();
        saveStudent.setAge(student.age());
        saveStudent.setEmail(student.email());
        saveStudent.setLastName(student.lastName());
        saveStudent.setFirstName(student.firstName());
        School school = new School();
        school.setId(student.schoolId());
        saveStudent.setSchool(school);
        return saveStudent;
    }
}
