package com.tpe.controller;

import com.tpe.domain.Author;
import com.tpe.domain.Publisher;
import com.tpe.dto.PublisherRequest;
import com.tpe.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publisher")  // http://localhost:8080/publisher
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public ResponseEntity<String> createPublisher(@Valid @RequestBody Publisher publisher){

        publisherService.createPublisher(publisher);
        String str = "Publisher is created successfully";

        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }

    @GetMapping("/publishers")
    public ResponseEntity<List<Publisher>> getPublisherAll(){

        List<Publisher> publishers = publisherService.getPublisherAll();

        return ResponseEntity.ok(publishers);
    }


    // get publisherr by id
    @GetMapping("{id}")
    public ResponseEntity<Publisher> getPublisherId(@PathVariable("id") Long id){
        Publisher publisher  =publisherService.getPublisherId(id);
        return ResponseEntity.ok(publisher);
    }


    // get publisher by name
    @GetMapping("publisherName")
    public ResponseEntity<List<Publisher>> getPublisherByName(@RequestParam("publisherName") String publisherName){

        List<Publisher> publishers = publisherService.findPublisherByName(publisherName);

        return ResponseEntity.ok(publishers);

    }

    // delete publisher
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable("id") Long id){
        publisherService.deletePublisher(id);
        String str= "This publisher has been deleted";

        return new ResponseEntity<>(str,HttpStatus.OK);

    }


    // update publisher
    @PutMapping("{id}")
    public ResponseEntity<String> updatePublisher(@PathVariable("id") Long id, @RequestBody PublisherRequest publisherRequest){
        publisherService.updatePublisher(id,publisherRequest);
        String str = "Publisher is update successfully";

        return new ResponseEntity<>(str,HttpStatus.OK);
    }




}
