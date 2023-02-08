package com.tpe.dto;

import com.tpe.domain.Book;
import com.tpe.domain.Publisher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherRequest {

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 25)
    private String publisher_name;



  //  private List<Book> books = new ArrayList<>();

    public PublisherRequest(Publisher publisher){
        this.publisher_name = publisher.getPublisherName();
      //  this.books = publisher.getBooks();
    }



}
