package com.java.books.controller;

import com.java.books.services.AuthorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity addAuthor(@RequestParam(name = "authorName") String authorname, @RequestParam(name = "contact") String contact) {
        return authorService.addAuthor(authorname, contact);

    }

    @PostMapping("/updateAuthorName")
    public ResponseEntity updateAuthor(@RequestParam(name = "authorId") int authorId,
                                       @RequestParam(name = "authorName") String authorName) {
        return authorService.updateAuthor(authorId, authorName);
    }

    @PostMapping("/deleteAuthorId")
    public ResponseEntity deleteAuthorId(@RequestParam(name = "authorId") int authorId) {
        return authorService.deleteAuthorId(authorId);
    }

    @GetMapping("/all-Author")
    public ResponseEntity getAllAuthor() {
        return authorService.getAllAuthor();
    }

    @GetMapping("/getAllAuthorWithBookResponse")
    public ResponseEntity getAllAuthorWithBookResponse(){
        return authorService.getListOfAuthorWithBookName();
    }
}
