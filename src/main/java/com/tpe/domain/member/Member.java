package com.tpe.domain.member;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @Column(length =11, unique = true, nullable = false)
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
