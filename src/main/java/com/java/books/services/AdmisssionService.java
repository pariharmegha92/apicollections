package com.java.books.services;

import com.java.books.Repository.AdmissionRepo;
import com.java.books.Repository.BranchRepository;
import com.java.books.Repository.StudentRepository;
import com.java.books.entity.AdmissionDetails;
import com.java.books.entity.BranchDetails;
import com.java.books.entity.StudentDetails;
import com.java.books.modelResponse.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdmisssionService {

    @Autowired
    private AdmissionRepo admissionRepo;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BranchRepository branchRepository;

    public ResponseEntity addStudent(int studentId, int branchId) {
        if (branchRepository.existsById(branchId)) {
            if (studentRepository.existsById(studentId)) {
                if (!admissionRepo.existsByStudentId(studentId)) {
                    StudentDetails studentDetails = studentRepository.findById(studentId).get();
                    String studentName = studentDetails.getStudentName();
                    BranchDetails branchDetails = branchRepository.findById(branchId).get();
                    String branchName = branchDetails.getBranchName();
                    //admission
                    AdmissionDetails admissionDetails = new AdmissionDetails();
                    admissionDetails.setBranchId(branchId);
                    admissionDetails.setBranchName(branchName);
                    admissionDetails.setStudentId(studentId);
                    admissionDetails.setStudentName(studentName);
                    AdmissionDetails adDetails = admissionRepo.save(admissionDetails);

                    //branch
                    branchDetails.setNoOfStudent(branchDetails.getNoOfStudent() + 1);
                    branchRepository.save(branchDetails);
                    return new ResponseEntity(adDetails, HttpStatus.OK);
                } else {
                    return new ResponseEntity(new MessageResponse(false, "This student already registered in our records"), HttpStatus.ALREADY_REPORTED);
                }
            } else {
                return new ResponseEntity(new MessageResponse(false, "Student doesn't exists our record!!"), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(new MessageResponse(false, "Branch doesn't exists our record!!"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteStudent(int studentId) {
        if (admissionRepo.existsByStudentId(studentId)) {
            AdmissionDetails admissionDetails = admissionRepo.findById(studentId).get();
            int branchId = admissionDetails.getBranchId();
            BranchDetails branchDetails = branchRepository.findById(branchId).get();
            branchDetails.setNoOfStudent(branchDetails.getNoOfStudent() - 1);
            branchRepository.save(branchDetails);

            int admissionId = admissionDetails.getAdmissionId();
            admissionRepo.deleteById(admissionId);//no value present;;
            return new ResponseEntity(new MessageResponse(true, "Successfully deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "StudentID doesn't exists in Db"), HttpStatus.NOT_FOUND);
        }
    }

    //update student in admission records

    public ResponseEntity updateStudent(int admissionId,int studentId, String studentName) {
        if (admissionRepo.existsById(admissionId)) {
            if (admissionRepo.existsByStudentId(studentId)) {
                AdmissionDetails admissionDetails1 = admissionRepo.findById(admissionId).get();//
                int studentId1 = admissionDetails1.getStudentId();
                if (studentId == studentId1) {
                    admissionDetails1.setStudentName(studentName);
                    admissionRepo.save(admissionDetails1);
                    return new ResponseEntity(new MessageResponse(true, "successfully Updated"), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new MessageResponse(false ,"you have proved this studentID thats doesn't exists in our record"),HttpStatus.NOT_FOUND);

                }
            }else{
                return new ResponseEntity(new MessageResponse(false ,"studentId doesn't exists in admission"),HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(new MessageResponse(false, "Please give proper studentId!!"), HttpStatus.NOT_FOUND);

        }
    }
}
