package com.springproject.demo.service;

import java.util.List;
import java.util.Optional;

import com.springproject.demo.dao.BookRepository;
import com.springproject.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository) {
        bookRepository = theBookRepository;
    }

    @Override
    public  List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int theId) {
        Optional<Book> result = bookRepository.findById(theId);

        Book theBook = null;

        if (result.isPresent()) {
            theBook = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find book id - " + theId);
        }

        return theBook;
    }

    @Override
    public Book save(Book theBook) {

        bookRepository.save(theBook);
        return theBook;

    }

    @Override
    public boolean deleteById(int theId) {

        bookRepository.deleteById(theId);
        return false;
    }


}





