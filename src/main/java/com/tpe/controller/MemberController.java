package com.tpe.controller;


import com.tpe.domain.member.Member;
import com.tpe.dto.MemberRequest;
import com.tpe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {


    @Autowired
    private MemberService memberService;



    @PostMapping
    public ResponseEntity<String> createMember(@RequestBody Member member){

        memberService.createMember(member);

        String str = "Member is create successfully";

        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }


    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMember(){
        List<Member> members =memberService.getMember();
        return ResponseEntity.ok(members);
    }


    @GetMapping("{id}")
    public ResponseEntity<Member> getMemberId(@PathVariable Long id){
        Member member= memberService.getMemberId(id);
        return ResponseEntity.ok(member);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long id){
        memberService.deleteMember(id);

        String str = "Member has been deleted";

        return new ResponseEntity<>(str,HttpStatus.OK);
    }



    @GetMapping("name")
    public ResponseEntity<List<Member>> getMemberByName(@RequestParam("name") String name){
        List<Member> members = memberService.findMember(name);
        return ResponseEntity.ok(members);
    }


    @GetMapping("lastName")
    public ResponseEntity<List<Member>> getMemberByLastName(@RequestParam("lastName") String lastName){
        List<Member> members = memberService.findMemberByLastName(lastName);
        return ResponseEntity.ok(members);
    }


    @GetMapping("/fullname")
    public ResponseEntity<List<Member>> getMemberByFullName(@RequestParam("name") String name,
                                                            @RequestParam("lastName") String lastName){
        List<Member> members = memberService.getMemberByFullName(name,lastName);
        return ResponseEntity.ok(members);

    }


    @PutMapping("{id}")
    public ResponseEntity<String> updateMember(@PathVariable("id") Long id, @RequestBody MemberRequest memberRequest){

        memberService.updateMember(id, memberRequest);

        String str = "Member is update successfully ";

        return new ResponseEntity<>(str, HttpStatus.OK);

    }


    @GetMapping("/page")
    public ResponseEntity<Page<Member>> getAllMemberByPage(@RequestParam(required = false,value = "page",defaultValue = "0") int page,
                                                           @RequestParam("size") int size ,
                                                           @RequestParam("sort") String prop,
                                                           @RequestParam("direction") Sort.Direction direction){

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction,prop));

        Page<Member> memberPage = memberService.getAllMemberByPage(pageable);
        return ResponseEntity.ok(memberPage);
    }




}
