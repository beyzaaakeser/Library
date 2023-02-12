package com.tpe.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpe.domain.member.DepositBook;

import com.tpe.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {

    @NotBlank
    private String identification_Number;

    @NotBlank
    private String member_FirstName;
    @NotBlank
    private String member_LastName;

    @Email(message = "Provide valid email")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
    private String phoneNumber;
    private String password;

    private List<DepositBook> depositBookList = new ArrayList<>();

    public MemberRequest(Member member) {
        this.identification_Number = member.getIdentificationNumber();
        this.member_FirstName = member.getMemberName();
        this.member_LastName = member.getMemberLastName() ;
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
        this.password = member.getPassword();
        this.depositBookList = member.getDepositBook();
    }
}