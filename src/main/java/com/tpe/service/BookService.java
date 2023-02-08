package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.dto.BookRequest;
import com.tpe.exeption.ConflictException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    public List<Book> getBook() {

        return bookRepository.findAll();
    }

    public void createKitap(BookRequest bookRequest) {

        boolean existsBySerino = bookRepository.existsBySerino(bookRequest.getBook_serial());

        if (existsBySerino) {
            throw new ConflictException("There is a book with this serial number :" + bookRequest.getBook_serial());
        }
        Book book = new Book();
        book.setName(bookRequest.getBook_name());
        book.setType(bookRequest.getBook_type());
        book.setSerino(bookRequest.getBook_serial());
        book.setIsbnNo(bookRequest.getBook_isbn());
        book.setYearOfPublication(bookRequest.getBook_year());

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


}
