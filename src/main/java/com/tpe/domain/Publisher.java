package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, length = 25)
    private String publisherName;

    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();


}
