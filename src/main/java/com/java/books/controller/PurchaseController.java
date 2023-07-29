package com.java.books.controller;

import com.java.books.Repository.PurchaseRepository;
import com.java.books.entity.PurchaseBook;
import com.java.books.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PurchaseRepository purchaseRepository;

    @PostMapping("/purchaseBook")
    public ResponseEntity purchaseBook(@RequestParam(name = "studentId") int studentId,@RequestParam(name = "bookId") int bookId){
        return purchaseService.purchaseBookByStudent(bookId,studentId);
    }

    @PostMapping("/purchaseBooktest")
    public PurchaseBook purchaseBooktest(@RequestBody PurchaseBook PurchaseBook){
        return purchaseRepository.save(PurchaseBook);
    }

    @GetMapping("/bookpurchaseresponse")
    public ResponseEntity getpurchasebookBystudentResponse(){
        return purchaseService.getListOfStudentDetailsWithBook();
    }
    @GetMapping("/getallBooks")
    public ResponseEntity getAllBook(){
        return purchaseService.getAllBookswithPurchaseDetails();
    }


    @PostMapping("/deletePurchaseById")
    public ResponseEntity deletePurchaseById(@RequestParam(name = "purchaseId")int purchaseId){
        return purchaseService.deleteByPurchaseId(purchaseId);
    }



}
