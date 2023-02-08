package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String authorNo;

    private String authorName;

    private String authorLastName;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();


}
