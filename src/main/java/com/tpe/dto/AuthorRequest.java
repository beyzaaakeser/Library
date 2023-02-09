package com.tpe.dto;

import com.tpe.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String author_no;

    @NotBlank
    @NotNull
    private String author_name;
    @NotBlank
    @NotNull
    private String author_last_name;


    //  private List<Book> books = new ArrayList<>();


    public AuthorRequest(Author author){
        this.author_no = author.getAuthorNo();
        this.author_name = author.getAuthorName();
        this.author_last_name = author.getAuthorLastName();
        //  this.books = author.getBooks();
    }


}