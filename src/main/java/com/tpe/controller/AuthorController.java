package com.tpe.controller;

import com.tpe.domain.Author;
import com.tpe.dto.AuthorRequest;
import com.tpe.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")  // http://localhost:8080/author
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @PostMapping
    public ResponseEntity<String> createAuthor(@Valid @RequestBody Author author){

        authorService.createAuthor(author);

        String str = "Author is create successfully ";

        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }


    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthor(){

       List<Author> authors = authorService.getAuthor();

       return ResponseEntity.ok(authors);
    }


    @GetMapping("{id}")
    public ResponseEntity<Author> getAuthorId(@PathVariable("id") Long id){
        Author author = authorService.getAuthorId(id);
        return ResponseEntity.ok(author);
    }

    // update

    @PutMapping("{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequest authorRequest){

        authorService.updateAuthor(id, authorRequest);

        String str = "Author is update successfully ";

        return new ResponseEntity<>(str, HttpStatus.OK);
    }






    // delete






    // get book by name




}
