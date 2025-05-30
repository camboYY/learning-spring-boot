package com.springboot.learning.student;

import com.springboot.learning.school.School;
import jakarta.persistence.*;

@Entity
@Table(name = "T_STUDENTS")
public class Student {
    @Id
    @GeneratedValue()
    private Integer id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private int age;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false, referencedColumnName = "id")
    private School school;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Student() {
    }

    public Student( String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
}

