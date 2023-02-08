package com.tpe.controller;

import com.tpe.domain.Publisher;
import com.tpe.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
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



}
