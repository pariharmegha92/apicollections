package com.java.books.Repository;

import com.java.books.entity.SubCategoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubCategoryDetails,Integer> {
    List<SubCategoryDetails> findByCategoryId(int categoryId);
}
