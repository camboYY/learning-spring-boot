package com.springboot.learning.school;

import com.springboot.learning.student.Student;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "T_SCHOOLS")
public class School {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "school")
    private Set<Student> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public School(){}

    public School(String name){
        this.name = name;
    }

}
