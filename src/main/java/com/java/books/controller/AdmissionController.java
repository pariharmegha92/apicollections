package com.java.books.controller;

import com.java.books.services.AdmisssionService;
import com.java.books.services.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdmissionController {

    @Autowired
    private AdmisssionService admisssionService;

    @Autowired
    private StudentService studentService;


    @PostMapping("/admissionStudent")
    public ResponseEntity addStudentInBranch(@RequestParam(name = "studentId")int studentId,@RequestParam(name = "branchId")int branchId){
        return admisssionService.addStudent(studentId,branchId);
    }

    @PostMapping("/deleteStudentId")
    public ResponseEntity deleteStudent(@RequestParam(name= "studentId")int studentId){
        return admisssionService.deleteStudent(studentId);

    }

    @PostMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestParam(name="admissionId")int admissionId,@RequestParam(name = "studentId") int studentId ,@RequestParam(name="studentName")String studentName){
        return admisssionService.updateStudent(admissionId,studentId,studentName);
    }
}
