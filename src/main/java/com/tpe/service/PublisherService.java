package com.tpe.service;

import com.tpe.domain.Publisher;
import com.tpe.dto.PublisherRequest;
import com.tpe.exeption.ConflictException;
import com.tpe.exeption.ResourceNotFountException;
import com.tpe.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public void createPublisher(Publisher publisher) {

        if(publisherRepository.existsByPublisherName(publisher.getPublisherName())){
            throw new ConflictException("There is a publisher registered with this id : " + publisher.getId());
        }
        publisherRepository.save(publisher);
    }


    public List<Publisher> getPublisherAll() {
        return publisherRepository.findAll();
    }


    public List<Publisher> findPublisherByName(String publisherName) {
       return publisherRepository.findByPublisherName(publisherName);

    }

    public void deletePublisher(Long id) {

        Publisher publisher = getPublisherId(id);
        publisherRepository.delete(publisher);

    }


    public Publisher getPublisherId(Long id) {
        return publisherRepository.findById(id).orElseThrow(()->
                new ResourceNotFountException("Publisher not found with id : "+id));
    }

    public void updatePublisher(Long id, PublisherRequest publisherRequest) {

        Publisher publisher = getPublisherId(id);

        if (publisherRequest.getPublisher_name() != null) publisher.setPublisherName(publisherRequest.getPublisher_name());

        publisherRepository.save(publisher);
    }

    public Page<Publisher> getAllWithPage(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }
}
