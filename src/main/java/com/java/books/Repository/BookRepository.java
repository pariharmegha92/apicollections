package com.java.books.Repository;

import com.java.books.entity.BookDetails;
import com.java.books.services.BookService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<BookDetails,Integer> {

   List<BookDetails> findByAuthorId(int authorId);

   List<BookDetails> findByPrices(String bookPrice);
}
