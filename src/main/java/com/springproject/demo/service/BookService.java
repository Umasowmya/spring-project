package com.springproject.demo.service;

import com.springproject.demo.entity.Book;

import java.util.List;

public interface BookService {

    public  List<Book> findAll();

    public Book findById(int theId);

    public Book save(Book theBook);

    public boolean deleteById(int theId);


}
