package com.vn.education.repository.postgres.source.custom;

import com.vn.education.entity.postgres.source.Student;

import java.util.List;

public interface StudentRepositoryCustom {
    List<Student> findStudentsByCustomCriteria(String criteria);
}

