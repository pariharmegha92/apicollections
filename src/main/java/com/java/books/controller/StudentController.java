package com.java.books.controller;

import com.java.books.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add-Student")
    public ResponseEntity addStudent(@RequestParam(name="studentName") String name , @RequestParam(name="Mobile")
                                     String mobile ,@RequestParam(name="emailId") String email){
        return studentService.addStudent(name,mobile,email);
    }

    @PostMapping("/deleteStudent")
    public ResponseEntity deleteStudent(@RequestParam(name="studentId")int studentId){
        return studentService.deleteStudent(studentId);
    }
}
