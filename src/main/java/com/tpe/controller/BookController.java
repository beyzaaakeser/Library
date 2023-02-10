package com.tpe.controller;

import com.tpe.domain.Book;
import com.tpe.dto.BookRequest;
import com.tpe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")  // http://localhost:8080/book
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBook(){
        List<Book> books = bookService.getBook();
        return ResponseEntity.ok(books);
    }

      /*
     public List<PostDto> getPosts(...) {
        //...
        List<Post> posts = postService.getPostsList(page, size, sortDir, sort);
        return posts.stream()
          .map(this::convertToDto)
          .collect(Collectors.toList());
    }
     */


    //http://localhost:8080/kitap/kitap?id=1
    @GetMapping("/book")
    public ResponseEntity<Book> getBookId(@RequestParam("id") Long id){
        Book book = bookService.getBookId(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/create") //http://localhost:8080/kitap/kitap + POST + JSON
    public ResponseEntity<String> createBook(@RequestBody BookRequest bookRequest){
        bookService.createBook(bookRequest);
        String str = "Book is create successfully";
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }


    // Kitap Update
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Long>> updateBook(@PathVariable("id") Long id, @RequestBody BookRequest bookRequest){

        bookService.updateBook(id, bookRequest);

        Map<String,Long> map = new HashMap<>();
        map.put("Book is updated successfully",id);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Long>> deleteBook(@PathVariable("id") Long id){

        bookService.deleteBook(id);
        Map<String, Long> map = new HashMap<>();
        map.put("This id book has been deleted : ", id);

        return ResponseEntity.ok(map);
    }


    @GetMapping("/name")
    public ResponseEntity<List<Book>> getBookByName(@RequestParam("name") String name){

        List<Book> list = bookService.findBook(name);

        return ResponseEntity.ok(list);

    }

    @GetMapping("/type")
    public ResponseEntity<List<Book>> getBookByType(@RequestParam("type") String type){
        List<Book> books= bookService.findBookByType(type);
        return ResponseEntity.ok(books);

    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<Book> getBookBySerial(@PathVariable("serial") String serial){
        Book book = bookService.findByserino(serial);
        return ResponseEntity.ok(book);
    }


    @GetMapping("/author/{id}")
    public ResponseEntity<List<Book>> authorBook(@PathVariable("id") Long id){
        List<Book> books = bookService.findAuthorBook(id);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<List<Book>> publisherBook(@PathVariable("id") Long id){
        List<Book> books = bookService.findPublisherBook(id);
        return ResponseEntity.ok(books);
    }







//    @PostMapping("/kitap_kayit") //http://localhost:8080/kitap/kitap_kayit + POST + JSON
//    public ResponseEntity<String> createBilgileriKitap(@RequestBody KitapDTO kitapDTO){
//
//        kitapService.createKitaplar(kitapDTO);
//
//        String str = "Kitaba ait tum bilgilerin kaydi yapildi";
//        return new ResponseEntity<>(str, HttpStatus.CREATED);
//    }


}
