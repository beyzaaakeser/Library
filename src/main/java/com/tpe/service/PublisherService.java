package com.tpe.service;

import com.tpe.domain.Publisher;
import com.tpe.exeption.ConflictException;
import com.tpe.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public void createPublisher(Publisher publisher) {

        if(publisherRepository.existsById(publisher.getId())){
            throw new ConflictException("There is a publisher registered with this id : " + publisher.getId());
        }
        publisherRepository.save(publisher);
    }


    public List<Publisher> getPublisherAll() {
        return publisherRepository.findAll();
    }
}
