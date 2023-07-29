package com.java.books.controller;

import com.java.books.Repository.BranchRepository;
import com.java.books.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {

    @Autowired
    BranchService branchService;

    @PostMapping("/addBranch")
    public ResponseEntity addBranch(@RequestParam(name = "branchName") String branchName){
        return branchService.addBranch(branchName);
    }

    @PostMapping("/deleteBranch")
    public ResponseEntity deleteBranch(@RequestParam(name="branchId") int branchId){
        return branchService.deleteBranch(branchId);
    }
    @GetMapping("/getAllBranchDetails")
public ResponseEntity getallBranchDetailswithStudentDetails(){
        return branchService.getLIstOfbranchWithStudentDetails();
}


}
