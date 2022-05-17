package com.springproject.demo.rest;

import com.springproject.demo.entity.Book;
import com.springproject.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {

	private BookService bookService;

	@Autowired
	public BookRestController(BookService theEmployeeService) {
		bookService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/books")
	public List<Book> findAll() {
		return bookService.findAll();
	}

	@GetMapping("/books/{bookId}")
	public Book getBook(@PathVariable int bookId) {

		Book theBook = bookService.findById(bookId);

		if (theBook == null) {
			throw new RuntimeException("Student id not found - " + bookId);
		}

		return theBook;
	}

	// add mapping for POST /employees - add new employee

	@PostMapping("/books")
	public Book addEmployee(@RequestBody Book theBook) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theBook.setId(0);

		bookService.save(theBook);

		return theBook;
	}

	@PutMapping("/books")
	public Book updateBook(@RequestBody Book theBook) {

		bookService.save(theBook);

		return theBook;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping("/books/{bookId}")
	public String deleteEmployee(@PathVariable int bookId) {

		 Book tempBook = bookService.findById(bookId);

		// throw exception if null

		if (tempBook == null) {
			throw new RuntimeException("book id not found - " + bookId);
		}

		bookService.deleteById(bookId);

		return "Deleted book id - " + bookId;
	}
	
}









