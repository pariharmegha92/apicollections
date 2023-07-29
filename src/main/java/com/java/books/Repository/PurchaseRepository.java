package com.java.books.Repository;

import com.java.books.entity.PurchaseBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseBook,Integer> {
    List<PurchaseBook> findByStudentId(int studentId);
    List<PurchaseBook> findByBookId(int bookId);

    boolean existsByBookIdAndStudentId(int bookId,int studentId);

}
