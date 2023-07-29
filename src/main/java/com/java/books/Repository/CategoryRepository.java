package com.java.books.Repository;

import com.java.books.entity.CategoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDetails,Integer> {
}
