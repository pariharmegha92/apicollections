package com.java.books.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubCategoryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subcategoryId;
    private String subcategoryName;
    private int categoryId;
    private String categoryName;
}
