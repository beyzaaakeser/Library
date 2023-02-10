package com.tpe.service;

import com.tpe.domain.Author;
import com.tpe.domain.Book;
import com.tpe.domain.Publisher;
import com.tpe.dto.BookRequest;
import com.tpe.exeption.ConflictException;
import com.tpe.repository.AuthorRepository;
import com.tpe.repository.BookRepository;
import com.tpe.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;


    public List<Book> getBook() {

        return bookRepository.findAll();
    }


    public void createBook(BookRequest bookRequest) {

        boolean existsBySerino = bookRepository.existsBySerino(bookRequest.getBook_serial());

        if (existsBySerino) {
            throw new ConflictException("There is a book with this serial number :" + bookRequest.getBook_serial());
        }

        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElseThrow(() ->
                new ConflictException(" The author for this id could not be found."));

        Publisher publisher = publisherRepository.findById(bookRequest.getPublisherId()).orElseThrow(() ->
                new ConflictException(" The author for this id could not be found."));


        Book book = new Book();
        book.setName(bookRequest.getBook_name());
        book.setType(bookRequest.getBook_type());
        book.setSerino(bookRequest.getBook_serial());
        book.setIsbnNo(bookRequest.getBook_isbn());
        book.setYearOfPublication(bookRequest.getBook_year());

        book.setAuthor(author);
        book.setPublisher(publisher);

        bookRepository.save(book);
    }

    public Book getBookId(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ConflictException(" The book for this id could not be found." + id));
    }

    public void updateBook(Long id, BookRequest bookRequest) {

        Book book = getBookId(id);

        if (bookRequest.getBook_name() != null) book.setName(bookRequest.getBook_name());
        if (bookRequest.getBook_type() != null) book.setType(bookRequest.getBook_type());
        if (bookRequest.getBook_year() != null) book.setYearOfPublication(bookRequest.getBook_year());
        if (bookRequest.getBook_serial() != null) book.setSerino(bookRequest.getBook_serial());
        if (bookRequest.getBook_isbn() != null) book.setIsbnNo(bookRequest.getBook_isbn());

        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = getBookId(id);
        bookRepository.delete(book);
    }

    public List<Book> findBook(String name) {
        return bookRepository.findByName(name);
    }


    public List<Book> findBookByType(String type) {
        return bookRepository.findByType(type);
    }

    public List<Book> findAuthorBook(Long id) {
       return bookRepository.findAuthorId(id);
    }

    public List<Book> findPublisherBook(Long id) {
        return bookRepository.findPublisherId(id);
    }


    public Book findByserino(String serial) {
        return bookRepository.findByserino(serial);
    }
}
