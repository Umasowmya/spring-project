package com.springproject.demo.service;


import com.springproject.demo.entity.Student;

import java.util.List;

public interface StudentService {

	public List<Student> findAll();
	
	public Student findById(int theId);
	
	public Student save(Student theStudent);
	
	public boolean deleteById(int theId);
	
}