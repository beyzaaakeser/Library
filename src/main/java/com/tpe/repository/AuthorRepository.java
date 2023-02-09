package com.tpe.repository;

import com.tpe.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByAuthorNo(String authorNo);

    List<Author> findByAuthorName(String name);

    List<Author> findByAuthorLastName(String authorLastName);


    @Query("SELECT a FROM Author a WHERE lower(a.authorName) LIKE %:pName% and lower(a.authorLastName) LIKE %:pLastName%")
    List<Author> AuthorNameAndAuthorLastName(@Param("pName")String name,
                                             @Param("pLastName") String lastname);

}