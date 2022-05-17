package com.springproject.demo.controller;

import com.springproject.demo.entity.Student;
import com.springproject.demo.entity.Book;
import com.springproject.demo.service.BookService;
import com.springproject.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    // add mapping for "/list"


    @GetMapping("/list")
    public String listBooks(Model theModel) {

        List<Book> theBooks = bookService.findAll();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String user=authentication.getName();
        theModel.addAttribute("user",user);

        // add to the spring model
        theModel.addAttribute("Books", theBooks);

        return "booksH/list-books";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Book theBook = new Book();
        List<Student> studentsO = studentService.findAll();
        theModel.addAttribute("student_list",studentsO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String user=authentication.getName();

        theModel.addAttribute("user",user);

        theModel.addAttribute("book", theBook);

        return "booksH/book-form";
    }


    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book")  @Valid Book theBook, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "booksH/book-form";
        }

        else {
            // save the Book
            bookService.save(theBook);

            // use a redirect to prevent duplicate submissions
            return "redirect:/book/list";
        }
    }
/*
    @GetMapping("/add_student")
    public String add_student(@RequestParam("bookId") int theId,
                                    Model theModel) {

        // get the Book from the service
        Book theBook = bookService.findById(theId);

        // set Book as a model attribute to pre-populate the form
        theModel.addAttribute("book", theBook);

        // send over to our form
        return "booksH/add-student-list";
    }

    @PostMapping("/save_student")
    public String save_student(@ModelAttribute("book") Book theBook) {

        // save the Book
        bookService.save(theBook);

        // use a redirect to prevent duplicate submissions
        return "redirect:/book/list";
    }

*/
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId,
                                    Model theModel) {

        // get the Book from the service
        Book theBook = bookService.findById(theId);

        // set Book as a model attribute to pre-populate the form
        theModel.addAttribute("book", theBook);
        List<Student> studentsO = studentService.findAll();
        theModel.addAttribute("student_list",studentsO);



        // send over to our form
        return "booksH/add-student-list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {

        // delete the Book
        bookService.deleteById(theId);

        // redirect to /Books/list
        return "redirect:/book/list";

    }



}