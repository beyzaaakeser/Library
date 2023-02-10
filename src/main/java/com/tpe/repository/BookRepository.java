package com.tpe.repository;

import com.tpe.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book, Long>{



   List<Book> findByName(String name);

    boolean existsBySerino(String bookSerial);


    List<Book> findByType(String type);



    @Query("SELECT b from Book b where b.author.id=:pId")
    List<Book> findAuthorId(@Param("pId") Long id);

    @Query("SELECT b from Book b where b.publisher.id=:pId")
    List<Book> findPublisherId(@Param("pId") Long id);


    Book findByserino(String serial);

}
