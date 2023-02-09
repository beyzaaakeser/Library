package com.tpe.dto;

import com.tpe.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotNull
    @NotBlank
    private String book_name;

    @NotNull
    @NotBlank
    private String book_type;

    @NotNull
    @NotBlank
    private String book_serial;

    @NotNull
    private String book_isbn;
    @NotNull
    @NotBlank
    private Integer book_year;


    private Long authorId;
    private Long publisherId;


    public BookRequest(Book book){
        this.book_name = book.getName();
        this.book_type = book.getType();
        this.book_serial = book.getSerino();
        this.book_isbn = book.getIsbnNo();
        this.book_year = book.getYearOfPublication();


    }


}
