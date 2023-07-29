package com.java.books.services;

import com.java.books.Repository.AdmissionRepo;
import com.java.books.Repository.BranchRepository;
import com.java.books.Repository.StudentRepository;
import com.java.books.entity.AdmissionDetails;
import com.java.books.entity.BranchDetails;
import com.java.books.entity.StudentDetails;
import com.java.books.modelResponse.AddBranchResponse;
import com.java.books.modelResponse.BranchDetailsResponse;
import com.java.books.modelResponse.MessageResponse;
import com.java.books.modelResponse.StudentnameWithDetailsResponse;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    AdmissionRepo admissionRepo;

    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity addBranch(String branchName) {
        BranchDetails branchDetails = new BranchDetails();
        branchDetails.setBranchName(branchName);
        branchDetails.setNoOfStudent(0);
        BranchDetails branch = branchRepository.save(branchDetails);
        if (branch != null) {
            return new ResponseEntity(new AddBranchResponse(true, "Successfully added branch", branch), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse(false, "something went wrong"), HttpStatus.NOT_FOUND);
    }


    //if I will delete any of the branch so that all student that would be associated with this branch in the admission that will also delete.
    public ResponseEntity deleteBranch(int branchId) {
        if (branchRepository.existsBybranchId(branchId)) {
           branchRepository.deleteById(branchId);

            List<AdmissionDetails> getAdmissionDetailsByBranchId = admissionRepo.findByBranchId(branchId);
            if(!getAdmissionDetailsByBranchId.isEmpty()) {
                for (AdmissionDetails admissionDetails : getAdmissionDetailsByBranchId) {
                    int admissionId = admissionDetails.getAdmissionId();
                    admissionRepo.deleteById(admissionId);
                }
                return new ResponseEntity(new MessageResponse(true, "Successfully deleted branch"), HttpStatus.OK);
            }else{
                return new ResponseEntity(new MessageResponse(false,"There is no student in this branch!!"),HttpStatus.NOT_FOUND);
            }

        }
        return new  ResponseEntity(new MessageResponse(false, "something went wrong"), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity getLIstOfbranchWithStudentDetails(){
        List<BranchDetails> branch = branchRepository.findAll();
        List<StudentnameWithDetailsResponse> branchDetailsResponseList = new ArrayList<>();
        for(BranchDetails branchDetails : branch){
            String branchName = branchDetails.getBranchName();
            int noOfStudent = branchDetails.getNoOfStudent();
            int branchId = branchDetails.getBranchId();
            List<StudentnameWithDetailsResponse> addStudentwithDetails = new ArrayList<>();
            List<AdmissionDetails> getAllstudentBybranchId = admissionRepo.findByBranchId(branchId);
            List<StudentDetails> addStudentList = new ArrayList<>();
            if(!getAllstudentBybranchId.isEmpty()){
                for(AdmissionDetails admissionDetails:getAllstudentBybranchId){
                    int studentId = admissionDetails.getStudentId();
                    StudentDetails studentDetails = studentRepository.findById(studentId).get();
                    addStudentList.add(studentDetails);
                }
                StudentnameWithDetailsResponse studentname = new StudentnameWithDetailsResponse(branchName,noOfStudent,addStudentList);
                branchDetailsResponseList.add(studentname);
            }

        }
        return new ResponseEntity(new BranchDetailsResponse(true,"Successfully added",branchDetailsResponseList),HttpStatus.OK);
    }
}
