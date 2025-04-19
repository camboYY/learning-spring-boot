package com.springboot.learning.student;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDto> getStudentsByFirstnameOrLastName (
            @RequestParam("firstName") String firstName
    ) {
        return service.getStudentsByFirstname(firstName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto createStudent(@Valid @RequestBody StudentRequestDto student) {
       return service.saveStudent(student);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeStudentById(@RequestParam("studentId") int studentId) {
        service.deleteStudent(studentId);
    }

    @GetMapping("/findById/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDto findStudentById(@PathVariable("studentId") int studentId) throws BadRequestException {
        return service.findStudentById(studentId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fielName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fielName, errorMessage);
        });

        return  new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
