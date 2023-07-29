package com.java.books.services;

import com.java.books.Repository.CategoryRepository;
import com.java.books.Repository.SubcategoryRepository;
import com.java.books.entity.CategoryDetails;
import com.java.books.entity.SubCategoryDetails;
import com.java.books.modelResponse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubcategoryService {
    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity addSubcategory(int categoryId, String subcategoryname) {
        if (categoryRepository.existsById(categoryId)) {
            SubCategoryDetails subCategoryDetails = new SubCategoryDetails();
            subCategoryDetails.setCategoryId(categoryId);
            CategoryDetails categoryDetails = categoryRepository.findById(categoryId).get();
            String categoryName = categoryDetails.getCategoryName();
            subCategoryDetails.setCategoryName(categoryName);
            subCategoryDetails.setSubcategoryName(subcategoryname);
            subcategoryRepository.save(subCategoryDetails);
            return new ResponseEntity(new AddSubcategoryResponse(true, "successfully added", subCategoryDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "ID doesn't exists in our data"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getBysubcategoryId(int subcategoryId) {
        if (subcategoryRepository.existsById(subcategoryId)) {
            SubCategoryDetails subCategoryDetails = subcategoryRepository.findById(subcategoryId).get();


            return new ResponseEntity(new AllSubcategoryResponse(true, "Successfully", subCategoryDetails), HttpStatus.OK);

        } else {
            return new ResponseEntity(new MessageResponse(false, "Id doesn't exists inour data"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getAllSubCategoryByCategoryId(int categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            List<SubCategoryDetails> listOfSubCategories = subcategoryRepository.findByCategoryId(categoryId);
            if (!listOfSubCategories.isEmpty()) {
                int size = listOfSubCategories.size();
                CategoryDetails categoryDetails = categoryRepository.findById(categoryId).get();
                String categoryName = categoryDetails.getCategoryName();
                GetSubCategoryByCategoryId getSubCategoryByCategoryId = new GetSubCategoryByCategoryId(true, "Successfully retrieve data", categoryName, size, listOfSubCategories);
                return new ResponseEntity(getSubCategoryByCategoryId, HttpStatus.OK);
            } else {
                return new ResponseEntity(new MessageResponse(false, "list is empry"), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(new MessageResponse(false, "Id doesn't exists"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteSubCategory(int subCategoryId) {
        if (subcategoryRepository.existsById(subCategoryId)) {
            subcategoryRepository.deleteById(subCategoryId);
            return new ResponseEntity(new MessageResponse(true, "Successfully Deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity(new MessageResponse(false, "id doesn't exists "), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getAllSubCategory() {
        List<SubCategoryDetails> allsubcategory = subcategoryRepository.findAll();
        return new ResponseEntity(new MessageResponse(true, "Successfully get all SubCategory"), HttpStatus.OK);
    }

    public ResponseEntity getSubCategoryByCategory(){
            List<CategoryDetails> categoryList = categoryRepository.findAll();
            List<GetAllSubCategoriesWithCategoryId> idList=new ArrayList<>();
            for(CategoryDetails categoryDetails : categoryList){
                int categoryId1 = categoryDetails.getCategoryId();
                String categoryName = categoryDetails.getCategoryName();
                List<SubCategoryDetails> subCategoryDetailsList = subcategoryRepository.findByCategoryId(categoryId1);
                if(!subCategoryDetailsList.isEmpty()) {
                    int size = subCategoryDetailsList.size();
                    GetAllSubCategoriesWithCategoryId getAllSubCategoriesWithCategoryId = new GetAllSubCategoriesWithCategoryId(categoryName, size, subCategoryDetailsList);
                    idList.add(getAllSubCategoriesWithCategoryId);
                }
            }
        return new ResponseEntity(new CategoryWithSubCategoryResponse(true,"succefull",idList),HttpStatus.OK);
    }

    public ResponseEntity getDetailsWithCategoryId(String categoriesId){
        String[] categoryArr = categoriesId.split(",");
        List<GetAllSubCategoriesWithCategoryId> idList=new ArrayList<>();
        for(int i=0;i< categoryArr.length;i++){
            String categoryIdString = categoryArr[i];
            int categoryId = Integer.parseInt(categoryIdString);
            if(categoryRepository.existsById(categoryId)) {
                CategoryDetails categoryDetails = categoryRepository.findById(categoryId).get();
                String categoryName = categoryDetails.getCategoryName();
                List<SubCategoryDetails> subCategoryList = subcategoryRepository.findByCategoryId(categoryId);
                if (!subCategoryList.isEmpty()) {
                    int size = subCategoryList.size();
                    GetAllSubCategoriesWithCategoryId getAllSubCategoriesWithCategoryId = new GetAllSubCategoriesWithCategoryId(categoryName, size, subCategoryList);
                    idList.add(getAllSubCategoriesWithCategoryId);
                }
            }else{
                return new ResponseEntity(new MessageResponse(false,"this id is not present in our data"),HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity(new CategoryWithSubCategoryResponse(true,"successfully retrieve data",idList),HttpStatus.OK);
    }


}
