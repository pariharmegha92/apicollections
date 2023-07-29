package com.java.books.services;

import com.java.books.Repository.CategoryRepository;
import com.java.books.Repository.SubcategoryRepository;
import com.java.books.entity.CategoryDetails;
import com.java.books.entity.SubCategoryDetails;
import com.java.books.modelResponse.AddCategoryResponse;
import com.java.books.modelResponse.AllCategoryResponse;
import com.java.books.modelResponse.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubcategoryRepository subcategoryRepository;

    public ResponseEntity addCategory(String categoryName, String colorCode) {
        CategoryDetails categoryDetails = new CategoryDetails();
        categoryDetails.setCategoryName(categoryName);
        categoryDetails.setColorCode(colorCode);
        CategoryDetails categoryDetails1 = categoryRepository.save(categoryDetails);
        return new ResponseEntity(new AddCategoryResponse(true, "Successfully added", categoryDetails1), HttpStatus.OK);
    }

    public ResponseEntity getAll() {
        List<CategoryDetails> categoryDetailsList = categoryRepository.findAll();
        return new ResponseEntity(new AllCategoryResponse(true, "Succefully", categoryDetailsList), HttpStatus.OK);

    }

    public ResponseEntity getCategoryById(int categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            CategoryDetails categoryDetails = categoryRepository.findById(categoryId).get();
            return new ResponseEntity(new AddCategoryResponse(true, "Successfully", categoryDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "Id doesn't exists in our data"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteCategory(int categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            List<SubCategoryDetails> subCategoryDetailsList = subcategoryRepository.findByCategoryId(categoryId);
            if (!subCategoryDetailsList.isEmpty()) {
                for (SubCategoryDetails subCategoryDetails1 : subCategoryDetailsList) {
                    int subcategoryId = subCategoryDetails1.getSubcategoryId();
                    subcategoryRepository.deleteById(subcategoryId);
                    return new ResponseEntity(new MessageResponse(true, "Successfully deleted"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(new MessageResponse(false, "Id doesn't exists in our data"), HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity(new MessageResponse(false,"CategoryId doesn't exists in our data "),HttpStatus.NOT_FOUND);
    }
}
