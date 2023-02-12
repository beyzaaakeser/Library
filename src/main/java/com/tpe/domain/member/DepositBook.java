package com.tpe.domain.member;

import com.tpe.domain.Book;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class DepositBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    private Member member;


    private LocalDate createDate = LocalDate.now();

    private LocalDate returnDate;

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = createDate.plusDays(15);
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }


}
