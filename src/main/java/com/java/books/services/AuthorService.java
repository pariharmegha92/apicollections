package com.java.books.services;

import com.java.books.Repository.AuthorRepository;
import com.java.books.Repository.BookRepository;
import com.java.books.entity.AuthorDetails;
import com.java.books.entity.BookDetails;
import com.java.books.modelResponse.*;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public ResponseEntity addAuthor(String name, String contact) {
        AuthorDetails authorDetails = new AuthorDetails();//hidden set id=1 authorDetails(1)
        authorDetails.setAuthorName(name);
        authorDetails.setContact(contact);
        authorDetails.setNoOfBooksPublished(0);
        AuthorDetails author = authorRepository.save(authorDetails);
        if (author != null) {
            return new ResponseEntity(new AddAuthorResponse(true, "Author successfully added", author), HttpStatus.OK);
        } else {
            return new ResponseEntity(new AddAuthorResponse(false, "author not save in database", author), HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity updateAuthor(int authorId, String authorName) {

        AuthorDetails authorDetails = authorRepository.findById(authorId).get();
        authorDetails.setAuthorName(authorName);
        AuthorDetails save = authorRepository.save(authorDetails);
        if (save == null) {
            return new ResponseEntity(new MessageResponse(false, "Something went wrong !!!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //change author name from books
        List<BookDetails> byAuthorId = bookRepository.findByAuthorId(authorId);
        for (BookDetails bookobj : byAuthorId) {
            bookobj.setAuthorName(authorName);
            bookRepository.save(bookobj);
        }

        return new ResponseEntity(new MessageResponse(true, "Successfully Updated"), HttpStatus.OK);
    }

    public ResponseEntity deleteAuthorId(int authorId) {
        if (authorRepository.existsById(authorId)) {
            List<BookDetails> byAuthorId1 = bookRepository.findByAuthorId(authorId);
            for (BookDetails bookDetails : byAuthorId1) {
                int bookId = bookDetails.getBookId();
                bookRepository.deleteById(bookId);
            }
            authorRepository.deleteById(authorId);
            return new ResponseEntity(new MessageResponse(true, "Successfully deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "id doesn't exists in db Please give proper id!!"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getAllAuthor() {
        List<AuthorDetails> allAuthor = authorRepository.findAll();
        return new ResponseEntity(new AddAuthorResponse(true, "Successfully added author", (AuthorDetails) allAuthor), HttpStatus.OK);
    }

    public ResponseEntity getListOfAuthorWithBookName(){
        List<AuthorDetails> author = authorRepository.findAll();
        List<AuthorNamewithDetailsResponse> authorNameWithBook=new ArrayList<>();
        for(AuthorDetails authorDetails : author){
            String authorName = authorDetails.getAuthorName();
            int noOfBooksPublished = authorDetails.getNoOfBooksPublished();
            int authorId = authorDetails.getAuthorId();
            List<BookNamePriceWithDetailResponse> addBookWithPrice=new ArrayList<>();
            List<BookDetails> getAllBooksByAuthorId = bookRepository.findByAuthorId(authorId);
            if(!getAllBooksByAuthorId.isEmpty()) {
                for(BookDetails bookDetails: getAllBooksByAuthorId){
                    String bookName = bookDetails.getBookName();
                    String prices = bookDetails.getPrices();
                    BookNamePriceWithDetailResponse price=new BookNamePriceWithDetailResponse(bookName,prices,bookDetails);
                    addBookWithPrice.add(price);
                }
                AuthorNamewithDetailsResponse namewithDetailsResponse = new AuthorNamewithDetailsResponse(authorName, noOfBooksPublished, addBookWithPrice);
                authorNameWithBook.add(namewithDetailsResponse);
            }
        }
        return new ResponseEntity(new AuthorResponsewithDetails(true,"successfully retrive",authorNameWithBook),HttpStatus.OK);
    }
}





