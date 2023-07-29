package com.java.books.controller;

import com.java.books.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @PostMapping("/addCategory")
    public ResponseEntity addCategory(@RequestParam(name="categoryName")String categoryName , @RequestParam(name="colorCode")String colorCode){
        return categoryService.addCategory(categoryName,colorCode);
    }

    @GetMapping("/allCategory")
    public ResponseEntity allCategory(){
        return categoryService.getAll();
    }

    @GetMapping("/getCategoryById")
    public ResponseEntity getCategoryById(@RequestParam(name="categoryId") int categoryID){
        return categoryService.getCategoryById(categoryID);
    }

    @PostMapping("/deleteCategory")
    public ResponseEntity deleteCategory(@RequestParam(name="categoryId")int categoryId){
        return categoryService.deleteCategory(categoryId);
    }

}
