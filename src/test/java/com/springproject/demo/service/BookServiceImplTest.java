package com.springproject.demo.service;

import com.springproject.demo.dao.BookRepository;
import com.springproject.demo.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookDAO;


    @Test
    void findAll() {
        Book book1= new Book("Marathi");
        Book book2 = new Book("German");
        book1.setId(100);
        book2.setId(101);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        Mockito.when(bookDAO.findAll()).thenReturn((bookList));
        assertThat(bookService.findAll()).isEqualTo(bookList);

    }

    @Test
    void findById() {
        Book book = getBook();
        book.setId(100);

        Mockito.when(bookDAO.findById(100)).thenReturn(Optional.of(book));
        assertThat(bookService.findById(100)).isEqualTo(book);
    }

    @Test
    void save() {
        Book book = getBook();

        Mockito.when(bookDAO.save(book)).thenReturn(book);
        assertThat(bookService.save(book)).isEqualTo(book);

    }

    @Test
    void deleteById() {
        Book book = getBook();
        book.setId(100);
        int id=book.getId();

        assertFalse(bookService.deleteById(book.getId()));
    }

    public Book getBook()
    {
        Book book = new Book("Economics");
        return book;
    }

}