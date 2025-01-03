package com.vn.education.service.serviceImpl;

import com.vn.education.entity.postgres.source.Student;
import com.vn.education.repository.postgres.source.StudentRepository;
import com.vn.education.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements  StudentService {

    private final StudentRepository studentRepository;


    @Override
    public void updateStudent() {

    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}
