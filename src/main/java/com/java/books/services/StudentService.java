package com.java.books.services;

import com.java.books.Repository.AdmissionRepo;
import com.java.books.Repository.BookRepository;
import com.java.books.Repository.PurchaseRepository;
import com.java.books.Repository.StudentRepository;
import com.java.books.entity.PurchaseBook;
import com.java.books.entity.StudentDetails;
import com.java.books.modelResponse.MessageResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    AdmissionRepo admissionRepo;

    @Autowired
    AdmisssionService admisssionService;

    public ResponseEntity addStudent(String name, String mobile, String email) {

        StudentDetails studentDetails = new StudentDetails();
        studentDetails.setStudentName(name);
        studentDetails.setMobile(mobile);
        studentDetails.setEmailId(email);
        StudentDetails student = studentRepository.save(studentDetails);
        if (student != null) {
            return new ResponseEntity( new MessageResponse(true,"Student successfully added"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false,"student not save in database"), HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity getBook(int studentId, int bookId) {//1,2,3
        if (studentRepository.existsById(studentId)) {

            return new ResponseEntity(new MessageResponse(true,"Successfully get books"),HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false,"books are not get by student"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteStudent(int studentId){
        if(studentRepository.existsById(studentId)){
            List<PurchaseBook> byStudentId = purchaseRepository.findByStudentId(studentId);
            for(PurchaseBook purchaseBook : byStudentId){
                int purchaseId = purchaseBook.getPurchaseId();
                purchaseService.deleteByPurchaseId(purchaseId);
            }
            admisssionService.deleteStudent(studentId);
            studentRepository.deleteById(studentId);
            return new ResponseEntity(new MessageResponse(true,"Successfully Deleted"),HttpStatus.OK);
        }else{
          return new ResponseEntity(new MessageResponse(false,"Student doesn't exists!"),HttpStatus.NOT_FOUND);
        }
    }


}


