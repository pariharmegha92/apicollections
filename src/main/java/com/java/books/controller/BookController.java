package com.java.books.controller;

import com.java.books.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestParam(name = "bookName") String bookname, @RequestParam(name = "authorId") int authorid, @RequestParam(name = "prices") String price) {
        return bookService.addBook(bookname, authorid, price);
    }

    @PostMapping("/delete-book")
    public ResponseEntity deleteBook(@RequestParam(name = "bookId") int id) {
        if (id > 0) {
           return bookService.deleteBook(id);
        } else {
            return new ResponseEntity("Please give us proper id!!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/all-Book")
    public ResponseEntity listofBooks() {
        return bookService.getAllBook();
    }

    @PostMapping("/update-book")
    public ResponseEntity updateBook(@RequestParam(name = "id") int id, @RequestParam(name = "bookName") String name) {
        if (id > 0) {
            return bookService.updateBook(id, name);
        } else {
            return new ResponseEntity("id doesn't exists", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/setBookPrice")
    public ResponseEntity setBookPriceBuAuthorId(@RequestParam(name = "authorId") int authorId, @RequestParam(name = "price") String price) {
        if (authorId > 0) {
            return bookService.setBookPrice(authorId, price);
        } else {
            return new ResponseEntity("id doesn't exists in the database", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getPrice")
    public ResponseEntity setPrice (@RequestParam(name = "price") String bookprice){
        return bookService.getPriceofBook(bookprice);
    }

    @GetMapping("/getAllBooksWithResponse")
    public ResponseEntity getAllBooksWithResponse(){
        return bookService.getListOfBooksWithPriceAndName();
    }
}
