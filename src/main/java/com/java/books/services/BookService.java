package com.java.books.services;

import com.java.books.Repository.AuthorRepository;
import com.java.books.Repository.BookRepository;
import com.java.books.entity.AuthorDetails;
import com.java.books.entity.BookDetails;
import com.java.books.modelResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public ResponseEntity addBook(String bookName, int authorId, String prices) {
        BookDetails book = null;
        if (authorRepository.existsById(authorId)) {
            BookDetails bookDetails = new BookDetails();
            bookDetails.setBookName(bookName);
            bookDetails.setBookPublishedTime(new Date());
            AuthorDetails authorDetails = authorRepository.findById(authorId).get();
            int noOfBooks = authorDetails.getNoOfBooksPublished();
            String authorName = authorDetails.getAuthorName();
            bookDetails.setAuthorName(authorName);
            bookDetails.setAuthorId(authorId);
            bookDetails.setPrices(prices);
            authorDetails.setNoOfBooksPublished(noOfBooks + 1);
            AuthorDetails author = authorRepository.save(authorDetails);
            System.out.println(author.getAuthorId() + " " + author.getAuthorName() + "  " + author.getNoOfBooksPublished());
            book = bookRepository.save(bookDetails);
            if (book != null) {
                return new ResponseEntity(new AddBookResponse(true, "Successfully book added", book), HttpStatus.OK);
            } else {
                return new ResponseEntity(new AddBookResponse(false, "Book not save in database", book), HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity(new AddBookResponse(false, "Author id doesn't exits in db!!", book), HttpStatus.NOT_FOUND);
        }
    }

//    public ResponseEntity getBookById(int bookId){
//        BookDetails bookDetails = bookRepository.findById(bookId).get();
//
//
//    }


    public ResponseEntity deleteBook(int bookId) {
        if (bookRepository.existsById(bookId)) {
            BookDetails bookDetails = bookRepository.findById(bookId).get();
            int authorId = bookDetails.getAuthorId();
            AuthorDetails authorDetails = authorRepository.findById(authorId).get();
            int noOfBooksPublished = authorDetails.getNoOfBooksPublished();
            authorDetails.setNoOfBooksPublished(noOfBooksPublished - 1);
            authorRepository.save(authorDetails);
            bookRepository.deleteById(bookId);
            return new ResponseEntity(new MessageResponse(true, "Successfully deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "Id doesn't exists in database..Please give proper id!"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getAllBook() {
        List<BookDetails> allBook = bookRepository.findAll();
        return new ResponseEntity(new GetBooksListResponse(true, "get all books successfully", allBook), HttpStatus.OK);
    }

    public ResponseEntity updateBook(int bookId, String bookName) {
        if (bookId > 0) {
            BookDetails bookDetails = bookRepository.findById(bookId).get();
            bookDetails.setBookName(bookName);
            bookRepository.save(bookDetails);
            return new ResponseEntity(new MessageResponse(true, "Successfully updated book name in bookDetails"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "Please give proper bookId!!"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity setBookPrice(int authorId, String bookPrice) {
        if (authorRepository.existsById(authorId)) {
            List<BookDetails> getBooksByAuthorId = bookRepository.findByAuthorId(authorId);//[14{},15{},16{},17{}]
            if (getBooksByAuthorId.isEmpty()) {
                return new ResponseEntity(new MessageResponse(false,"No any books related to this author id"), HttpStatus.NOT_FOUND);
            }
            for (BookDetails bookDetails : getBooksByAuthorId) {
                int bookId = bookDetails.getBookId();
                BookDetails bookDetails1 = bookRepository.findById(bookId).get();
                bookDetails1.setPrices(bookPrice);
                bookRepository.save(bookDetails1);
            }
            return new ResponseEntity(new MessageResponse(true, "book price is add in db"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "Author doesn't exists!!"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getPriceofBook(String bookPrice) {
        List<BookDetails> getBookBybookPrice = bookRepository.findByPrices(bookPrice);
        if (!getBookBybookPrice.isEmpty()) {
//            GetBooksListResponse getBooksByPriceResponse=new GetBooksListResponse();
//            getBooksByPriceResponse.setResult(true);
//            getBooksByPriceResponse.setMessage("Successfully retrieve");
//            getBooksByPriceResponse.setData(getBookBybookPrice);
            return new ResponseEntity(new GetBooksListResponse(true, "Successfully retrieve", getBookBybookPrice), HttpStatus.OK);
        } else {
            return new ResponseEntity(new GetBooksListResponse(false, "book doesn't exists price", getBookBybookPrice), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getListOfBooksWithPriceAndName(){
        List<BookDetails> books = bookRepository.findAll();
        BookNamePriceWithDetailResponse[] bookNamePriceWithDetailResponses=new BookNamePriceWithDetailResponse[books.size()];
//        List<BookNamePriceWithDetailResponse> detailResponseList=
        int i=0;
        for (BookDetails bookDetails : books){
            String bookName = bookDetails.getBookName();
            String prices = bookDetails.getPrices();
            bookNamePriceWithDetailResponses[i]=new BookNamePriceWithDetailResponse(bookName,prices,bookDetails);
//            detailResponseList.add(bookNamePriceWithDetailResponses[i]);
            i++;
        }
        BooksResponseWithName booksResponseWithName=new BooksResponseWithName(true,"successfully retrive",bookNamePriceWithDetailResponses);
//        BooksResponseWithName booksResponseWithName=new BooksResponseWithName(true,"successfully retrive",detailResponseList);
        return new ResponseEntity(booksResponseWithName,HttpStatus.OK);
    }
}






