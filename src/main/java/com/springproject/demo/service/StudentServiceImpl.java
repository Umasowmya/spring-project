package com.springproject.demo.service;

import java.util.List;
import java.util.Optional;

import com.springproject.demo.dao.StudentRepository;
import com.springproject.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;


    @Autowired
    public StudentServiceImpl(StudentRepository theStudentRepository) {
        studentRepository = theStudentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int theId) {
        Optional<Student> result = studentRepository.findById(theId);

        Student theStudent = null;

        if (result.isPresent()) {
            theStudent = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find student id - " + theId);
        }

        return theStudent;
    }


    @Override
    public Student save(Student theStudent) {
        studentRepository.save(theStudent);
        return theStudent;
    }

    @Override
    public boolean deleteById(int theId) {
        studentRepository.deleteById( theId);
        return false;
    }

}





