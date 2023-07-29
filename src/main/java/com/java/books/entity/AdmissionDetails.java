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
public class AdmissionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admissionId;
    private String studentName;
    private int studentId;
    private int branchId;
    private String branchName;

}


//findById(--).get();

//existsById(--);
