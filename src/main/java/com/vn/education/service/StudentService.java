package com.vn.education.service;

import com.vn.education.entity.postgres.source.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    void updateStudent();

    Optional<Student> findById(Long id);


    List<Student> findAll();


}
