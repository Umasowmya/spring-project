package com.springproject.demo.rest;

import com.springproject.demo.entity.Student;
import com.springproject.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private StudentService studentService;

	@Autowired
	public StudentRestController(StudentService theEmployeeService) {
		studentService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/students")
	public List<Student> findAll() {
		return studentService.findAll();
	}

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		Student theStudent = studentService.findById(studentId);

		if (theStudent == null) {
			throw new RuntimeException("Student id not found - " + studentId);
		}

		return theStudent;
	}

	// add mapping for POST /employees - add new employee

	@PostMapping("/students")
	public Student addStudent(@RequestBody Student theStudent) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theStudent.setId(0);

		studentService.save(theStudent);

		return theStudent;
	}

	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student theStudent) {

		studentService.save(theStudent);

		return theStudent;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable int studentId) {

		Student tempStudent = studentService.findById(studentId);

		// throw exception if null

		if (tempStudent == null) {
			throw new RuntimeException("student id not found - " + studentId);
		}

		studentService.deleteById(studentId);

		return "Deleted student id - " + studentId;
	}
	
}









