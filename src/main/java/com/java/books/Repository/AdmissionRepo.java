package com.java.books.Repository;

import com.java.books.entity.AdmissionDetails;
import com.java.books.entity.BranchDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdmissionRepo extends JpaRepository<AdmissionDetails,Integer> {
    boolean existsByStudentId(int studentId);

    AdmissionDetails findByStudentId(int studentId);

    List<AdmissionDetails> findByBranchId(int branchId);

    void deleteByStudentId(int studentId);

}
