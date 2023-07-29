package com.java.books.controller;

import com.java.books.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubcategoryController {
    @Autowired
    SubcategoryService subcategoryService;

    @PostMapping("/addSubcategory")
    public ResponseEntity addSubcategory(@RequestParam(name="categoryId")int categoryId,@RequestParam(name="subCategoryName")String subcategoryName){
        return subcategoryService.addSubcategory(categoryId,subcategoryName);
    }

    @PostMapping("/getIdBySubcategory")
    public ResponseEntity getBysubcategoryId(@RequestParam(name="subcategoryId")int subcategoryId){
        return subcategoryService.getBysubcategoryId(subcategoryId);
    }

    @GetMapping("/getSubCategoryByCategoryId")
    public ResponseEntity getSubCategorybyCategoryId(@RequestParam(name="categoryId")int categoryId){
        return subcategoryService.getAllSubCategoryByCategoryId(categoryId);
    }
    @PostMapping("/deleteSubCategory")
    public ResponseEntity deleteSubCategoryId(@RequestParam(name="subCategoryId")int subCategoryId){
        return subcategoryService.deleteSubCategory(subCategoryId);
    }

    @GetMapping("/getAllSubCategory")
    public ResponseEntity getAllSubCategory(){
        return subcategoryService.getAllSubCategory();
    }


    @GetMapping("/getSubCategoryByCategory")
    public ResponseEntity getSubCategoryByCategory(){
        return subcategoryService.getSubCategoryByCategory();
    }
   @GetMapping("/getDetailsWithCategoriesId")
    public ResponseEntity getDetailsWithCategoryId(@RequestParam(name= "categoriesId")String categoriesId){
        return subcategoryService.getDetailsWithCategoryId(categoriesId);
    }
}
