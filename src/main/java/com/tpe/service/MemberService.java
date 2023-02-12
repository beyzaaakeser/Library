package com.tpe.service;

import com.tpe.domain.member.Member;
import com.tpe.dto.MemberRequest;
import com.tpe.exeption.ConflictException;
import com.tpe.exeption.ResourceNotFountException;
import com.tpe.repository.AuthorRepository;
import com.tpe.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AuthorRepository authorRepository;


    public void createMember(Member member) {

        if( memberRepository.existsByIdentificationNumber(member.getIdentificationNumber()) ||

                memberRepository.existsByEmail(member.getEmail())){
            throw new ConflictException("Member IdentificationNumber or Email is already exists. " );
        }

        memberRepository.save(member);
    }


    public List<Member> getMember() {
        return memberRepository.findAll();
    }

    public Member getMemberId(Long id) {
        return memberRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFountException("Member not found with id : "+id));
    }

    public void deleteMember(Long id) {

        Member member = getMemberId(id);
        memberRepository.delete(member);
    }

    public List<Member> findMember(String name) {
        return memberRepository.findByMemberName(name);
    }

    public List<Member> findMemberByLastName(String lastName) {
        return memberRepository.findByMemberLastName(lastName);
    }

    public List<Member> getMemberByFullName(String name, String lastName) {
        String lowerName = name.toLowerCase();
        String lowerLastName = lastName.toLowerCase();
        return memberRepository.MemberNameAndLastName(lowerName, lowerLastName);
    }

    public void updateMember(Long id, MemberRequest memberRequest) {

        boolean existEmail = memberRepository.existsByEmail(memberRequest.getEmail());
        boolean existPhoneNumber = memberRepository.existsByPhoneNumber(memberRequest.getPhoneNumber());
        boolean identificationNumber = memberRepository.existsByIdentificationNumber(memberRequest.getIdentification_Number());

        Member member = getMemberId(id);

        if(existEmail && !memberRequest.getEmail().equals(member.getEmail())) {
            throw new ConflictException("Email is already exist ");
        }

        if(existPhoneNumber && !memberRequest.getPhoneNumber().equals(member.getPhoneNumber())) {
            throw new ConflictException("Phone Number is already exist ");
        }

        if(identificationNumber && !memberRequest.getIdentification_Number().equals(member.getIdentificationNumber())) {
            throw new ConflictException("Identification Numberis already exist ");
        }

        if(memberRequest.getMember_FirstName()!=null) member.setMemberName(memberRequest.getMember_FirstName());
        if(memberRequest.getMember_LastName()!=null) member.setMemberLastName(memberRequest.getMember_LastName());
        if(memberRequest.getPhoneNumber() !=null) member.setPhoneNumber(memberRequest.getPhoneNumber());
        if(memberRequest.getEmail() !=null) member.setEmail(memberRequest.getEmail());
        if(memberRequest.getIdentification_Number() !=null) member.setIdentificationNumber(memberRequest.getIdentification_Number());
        if(memberRequest.getPassword() !=null) member.setPassword(memberRequest.getPassword());
        if(memberRequest.getDepositBookList() !=null) member.setDepositBook(memberRequest.getDepositBookList());

        memberRepository.save(member);
    }

}
