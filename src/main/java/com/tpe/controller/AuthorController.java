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

    // get all author
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthor(){

        List<Author> authors = authorService.getAuthor();

        return ResponseEntity.ok(authors);
    }

    // get author by id
    @GetMapping("{id}")
    public ResponseEntity<Author> getAuthorId(@PathVariable Long id){

        Author author = authorService.getAuthorId(id);
        return ResponseEntity.ok(author);
    }

    // get Author By Name
    @GetMapping("name")
    public ResponseEntity<List<Author>> getAuthorByName(@RequestParam("name") String name){

        List<Author> authorList = authorService.findAuthor(name);

        return ResponseEntity.ok(authorList);

    }

    // get Author By Last Name
    @GetMapping("authorLastName")
    public ResponseEntity<List<Author>> getAuthorByLastName(@RequestParam("authorLastName") String authorLastName){

        List<Author> authorList = authorService.findAuthorByLastName(authorLastName);

        return ResponseEntity.ok(authorList);

    }

    // get author by name and last name

    @GetMapping("/fullname")
    public ResponseEntity<List<Author>> authors(@RequestParam("name") String name,
                                                @RequestParam("lastname") String lastname){

        List<Author> authors = authorService.getAllAuthorByFullName(name, lastname);

        return ResponseEntity.ok(authors);
    }


    // Update author
    @PutMapping("{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequest authorRequest){

        authorService.updateAuthor(id, authorRequest);

        String str = "Author is update successfully ";

        return new ResponseEntity<>(str, HttpStatus.OK);
    }


    // delete author
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
        String str = "This author has been deleted";

        return new ResponseEntity<>(str, HttpStatus.OK);
    }




}
