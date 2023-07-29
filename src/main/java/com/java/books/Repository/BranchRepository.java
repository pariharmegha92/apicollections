package com.java.books.Repository;

import com.java.books.entity.AdmissionDetails;
import com.java.books.entity.BranchDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<BranchDetails,Integer> {
    boolean existsBybranchId(int branchId);
//    List<AdmissionDetails> findByadmissionId(int admissionId);
}
