package com.java.books.services;

import com.java.books.Repository.BookRepository;
import com.java.books.Repository.PurchaseRepository;
import com.java.books.Repository.StudentRepository;
import com.java.books.entity.BookDetails;
import com.java.books.entity.PurchaseBook;
import com.java.books.entity.StudentDetails;
import com.java.books.modelResponse.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public PurchaseRepository purchaseRepository;

    public ResponseEntity purchaseBookByStudent(int bookId, int studentId) {
        if (studentRepository.existsById(studentId)) {
            if (bookRepository.existsById(bookId)) {
                if(!purchaseRepository.existsByBookIdAndStudentId(bookId,studentId)) {
                    PurchaseBook pBook = new PurchaseBook();
                    pBook.setBookId(bookId);
                    pBook.setStudentId(studentId);
                    pBook.setPurchaseDate(new Date());

                    StudentDetails studentDetails = studentRepository.findById(studentId).get();
                    int noOfBookPurchase = studentDetails.getNoOfBookPurchase();
                    studentDetails.setNoOfBookPurchase(noOfBookPurchase + 1);
                    studentRepository.save(studentDetails);


                    BookDetails bookDetails = bookRepository.findById(bookId).get();
                    bookDetails.setGetnoOfBookpurchase(bookDetails.getGetnoOfBookpurchase() + 1);
                    bookRepository.save(bookDetails);

                    PurchaseBook purchaseBook = purchaseRepository.save(pBook);
                    return ResponseEntity.ok(purchaseBook);
                }else{
                    return new ResponseEntity(new MessageResponse(false,"You have already purchased this book!"),HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new MessageResponse(false, "this book not exists in our records!!"), HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity(new MessageResponse(false, "Student not exists in our dataset!!"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteByPurchaseId(int purchaseId){
        if(purchaseRepository.existsById(purchaseId)){

            PurchaseBook book = purchaseRepository.findById(purchaseId).get();

            //student se no of books minus kiya
            int studentId = book.getStudentId();
            StudentDetails studentDetails = studentRepository.findById(studentId).get();
            int noOfBookPurchase = studentDetails.getNoOfBookPurchase();
            studentDetails.setNoOfBookPurchase(noOfBookPurchase-1);
            studentRepository.save(studentDetails);

            //book se bhi minus kiya
            int bookId = book.getBookId();
            BookDetails bookDetails = bookRepository.findById(bookId).get();
            int getnoOfBookpurchase = bookDetails.getGetnoOfBookpurchase();
            bookDetails.setGetnoOfBookpurchase(getnoOfBookpurchase-1);
            bookRepository.save(bookDetails);

            purchaseRepository.deleteById(purchaseId);
            MessageResponse messageResponse=new MessageResponse(true,"Delete Successfully!!");
            return new ResponseEntity(messageResponse,HttpStatus.OK);

        }else{
            return new ResponseEntity(new MessageResponse(false,"Purchase id doesn't exists!"),HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getListOfStudentDetailsWithBook(){
        List<StudentDetails> studentDetails = studentRepository.findAll();
        List<StudentDetailsWithBook> storeFinalData = new ArrayList<>();
        for(StudentDetails studentDetails1 : studentDetails){
            String studentName = studentDetails1.getStudentName();
            String emailId = studentDetails1.getEmailId();
            int studentId = studentDetails1.getStudentId();
            List<PurchaseBook> byStudentId = purchaseRepository.findByStudentId(studentId);
            int noOfBookPurchase = byStudentId.size();
            List<BookDetails> bookDetailsList =new ArrayList<>();
            for(PurchaseBook bookDetailsInPBill : byStudentId){
                int bookId = bookDetailsInPBill.getBookId();
                BookDetails bookDetails = bookRepository.findById(bookId).get();
                bookDetailsList.add(bookDetails);
            }
            StudentDetailsWithBook studentDetailsWithBook =new StudentDetailsWithBook(studentName,emailId,noOfBookPurchase,bookDetailsList);
            storeFinalData.add(studentDetailsWithBook);
        }
        return new ResponseEntity(new PurchaseBookResponse(true,"Successfully retirve data!!",storeFinalData),HttpStatus.OK);
    }
    public ResponseEntity getAllBookswithPurchaseDetails() {
        List<BookDetails> bookDetailsList = bookRepository.findAll();
        List<PurchaseBookwithStudentResponse> addBooksWithPurchaseDetails = new ArrayList<>();
        for (BookDetails book : bookDetailsList) {
            String bookName = book.getBookName();
            int bookId = book.getBookId();
            List<PurchaseBook> byBookId = purchaseRepository.findByBookId(bookId);
            int size = byBookId.size();
            if(!byBookId.isEmpty()) {
                List<StudentDetails> studentDetailsList = new ArrayList<>();
                for (PurchaseBook studentDetails : byBookId) {
                    int studentId = studentDetails.getStudentId();
                    StudentDetails studentDetails1 = studentRepository.findById(studentId).get();
                    studentDetailsList.add(studentDetails1);
                }
                PurchaseBookwithStudentResponse purchaseBookwithStudentResponse = new PurchaseBookwithStudentResponse(bookName, size, studentDetailsList);
                addBooksWithPurchaseDetails.add(purchaseBookwithStudentResponse);
            }
        }

        BookDetailswithPurchaseDetailsResponse finalcontailner=new BookDetailswithPurchaseDetailsResponse(true,"Successfully",addBooksWithPurchaseDetails);

        return new ResponseEntity(finalcontailner,HttpStatus.OK);
    }

}


