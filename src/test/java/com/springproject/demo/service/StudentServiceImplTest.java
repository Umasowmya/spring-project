package com.springproject.demo.service;

import com.springproject.demo.dao.StudentRepository;
import com.springproject.demo.entity.Student;
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
class StudentServiceImplTest {
    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentDAO;


    @Test
    void findAll() {
        Student Student1= new Student("Stephen","Williams","stephen@gmail.com");
        Student Student2 = new Student("Damon","Williams","damon@gmail.com");
        Student1.setId(100);
        Student2.setId(101);

        List<Student> StudentList = new ArrayList<>();
        StudentList.add(Student1);
        StudentList.add(Student2);

        Mockito.when(studentDAO.findAll()).thenReturn((StudentList));
        assertThat(studentService.findAll()).isEqualTo(StudentList);

    }

    @Test
    void findById() {
        Student student = getStudent();
        student.setId(100);

        Mockito.when(studentDAO.findById(100)).thenReturn(Optional.of(student));
        assertThat(studentService.findById(100)).isEqualTo(student);
    }

    @Test
    void save() {
        Student student = getStudent();

        Mockito.when(studentDAO.save(student)).thenReturn(student);
        assertThat(studentService.save(student)).isEqualTo(student);

    }

    @Test
    void deleteById() {
        Student student = getStudent();
        student.setId(100);
        int id=student.getId();

        assertFalse(studentService.deleteById(student.getId()));
    }

    public Student getStudent()
    {
        Student student = new Student("Ellen","Weems","ellen@gmail.com");
        return student;
    }

}