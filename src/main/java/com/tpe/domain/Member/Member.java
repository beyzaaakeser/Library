package com.tpe.domain.Member;

import com.tpe.domain.Book;
import lombok.*;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;


    @NotBlank
    @Column(unique = true,nullable = false, length = 11)
    private String identificationNumber;

    @NotBlank
    @Column(nullable = false, length = 25)
    private String memberName;

    @NotBlank
    @Column(nullable = false, length = 25)
    private String memberLastName;

    @Column(nullable = false, length = 15,unique = true)
    private String phoneNumber;

    @Column(nullable = false, length = 55,unique = true)
    @Email(message = "Provide valid email.")
    private String email;

    @Column(nullable = false, length = 25)
    private String password;


    @OneToMany
    private List<DepositBook> depositBook = new ArrayList<>();

}
