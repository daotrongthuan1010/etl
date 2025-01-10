package com.vn.education.service;

import com.vn.education.dto.StudentDTO;
import com.vn.education.entity.postgres.source.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    void save(StudentDTO dto);
    void updateStudent();

    StudentDTO findById(Long id) throws ClassNotFoundException;


    List<StudentDTO> findAll();


}
