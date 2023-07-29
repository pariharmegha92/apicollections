package com.java.books.Repository;

import com.java.books.entity.AuthorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface AuthorRepository extends JpaRepository<AuthorDetails,Integer> {
}
