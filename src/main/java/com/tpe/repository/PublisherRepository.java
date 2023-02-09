package com.tpe.repository;

import com.tpe.domain.Author;
import com.tpe.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {


    boolean existsByPublisherName(String publisherName);


    List<Publisher> findByPublisherName(String publisherName);

}
