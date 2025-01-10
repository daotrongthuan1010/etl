package com.vn.education.service.serviceImpl;

import com.vn.education.dto.StudentDTO;
import com.vn.education.entity.postgres.source.Student;
import com.vn.education.repository.postgres.source.StudentRepository;
import com.vn.education.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public void save(StudentDTO dto) {
        studentRepository.save(Student.builder()
                .firstName(dto.getFirstName())
                .dob(dto.getDob())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .enrollmentDate(dto.getEnrollmentDate())
                .build());
    }

    @Override
    public void updateStudent(StudentDTO dto) {
            studentRepository.save(Student.builder()
                            .phoneNumber(dto.getPhoneNumber())
                            .email(dto.getEmail())
                            .firstName(dto.getFirstName())
                            .enrollmentDate(dto.getEnrollmentDate())
                    .build());
    }

    @Override
    public StudentDTO findById(Long id) throws ClassNotFoundException {
        Student student  = studentRepository.findById(id).orElseThrow(()-> new RuntimeException());
        return StudentDTO.builder()
                .address(student.getAddress())
                .gender(student.getGender())
                .dob(student.getDob())
                .email(student.getEmail())
                .build();
    }

    @Override
    public List<StudentDTO> findAll() {
        List<Student> list = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
         list.forEach(x -> {
             studentDTOList.add(StudentDTO.builder()
                     .lastName(x.getLastName())
                     .phoneNumber(x.getPhoneNumber())
                     .email(x.getEmail())
                     .dob(x.getDob())
                     .gender(x.getGender())
                     .enrollmentDate(x.getEnrollmentDate())
                     .lastName(x.getLastName())
                     .firstName(x.getFirstName())
                     .build());
        });
         return  studentDTOList;
    }
}
