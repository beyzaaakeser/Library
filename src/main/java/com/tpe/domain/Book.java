package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpe.domain.Member.DepositBook;
import com.tpe.domain.Member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String name;

    @Column(nullable = false, length = 25)
    private String type;

    @NotNull
    @NotBlank
    @Column(unique = true, length = 20)
    private String serino;

    @NotNull
    @NotBlank
    @Column(unique = true, length = 20)
    private String isbnNo;

    
    private Integer yearOfPublication;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


    @OneToOne
    private Member member;
}
