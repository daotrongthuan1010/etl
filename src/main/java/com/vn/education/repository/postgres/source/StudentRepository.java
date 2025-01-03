package com.vn.education.repository.postgres.source;

import com.vn.education.entity.postgres.source.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}

