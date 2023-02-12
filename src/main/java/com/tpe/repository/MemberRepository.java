package com.tpe.repository;

import com.tpe.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    boolean existsByIdentificationNumber(String identificationNumber);

    boolean existsByEmail(String email);

    List<Member> findByMemberName(String name);


    List<Member> findByMemberLastName(String lastName);

    @Query("SELECT m FROM Member m WHERE lower(m.memberName) LIKE %:pName% and lower(m.memberLastName) LIKE %:pLastName%")
    List<Member> MemberNameAndLastName(@Param("pName")String name,
                                       @Param("pLastName") String lastname);


    boolean existsByPhoneNumber(String phoneNumber);

}
