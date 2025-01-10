package com.vn.education.controller;

import com.vn.education.dto.StudentDTO;
import com.vn.education.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Void> add(StudentDTO dto){
        studentService.save(dto);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDTO>> getAll(StudentDTO dto){
        List<StudentDTO> list = studentService.findAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(StudentDTO dto){
        studentService.updateStudent(dto);
    }




}
