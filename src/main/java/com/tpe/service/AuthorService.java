package com.tpe.service;

import com.tpe.domain.Author;
import com.tpe.dto.AuthorRequest;
import com.tpe.exeption.ConflictException;
import com.tpe.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    public List<Author> getAuthor() {
        return authorRepository.findAll();
    }

    public void createAuthor(Author author) {

        if(authorRepository.existsByAuthorNo(author.getAuthorNo())) {
            throw new ConflictException("Author No is already exists. " + author.getAuthorNo());
        }

        authorRepository.save(author);

    }

    public Author getAuthorId(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new ConflictException(" The author for this id could not be found." + id));
    }


    public void updateAuthor(Long id, AuthorRequest authorRequest) {

        Author author = getAuthorId(id);
        if(authorRequest.getAuthor_no()!=null) author.setAuthorNo(authorRequest.getAuthor_no());
        if(authorRequest.getAuthor_name() != null) author.setAuthorName(authorRequest.getAuthor_name());
        if(authorRequest.getAuthor_last_name() !=null) author.setAuthorLastName(authorRequest.getAuthor_last_name());

        authorRepository.save(author);
    }
}
