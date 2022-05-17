package com.springproject.demo.controller;

import com.springproject.demo.entity.Student;
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
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    // add mapping for "/list"


    @GetMapping("/list")
    public String listStudents(Model theModel) {

        List<Student> theStudents = studentService.findAll();

        // add to the spring model
        theModel.addAttribute("students", theStudents);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String user=authentication.getName();

        theModel.addAttribute("user",user);

        return "studentsH/list-students";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Student theStudent = new Student();

        theModel.addAttribute("student", theStudent);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String user=authentication.getName();

        theModel.addAttribute("user",user);

        return "studentsH/student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") @Valid Student theStudent, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "studentsH/student-form";
        }

        else {
            // save the Student
            studentService.save(theStudent);

            // use a redirect to prevent duplicate submissions
            return "redirect:/student/list";
        }
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int theId,
                                    Model theModel) {

        // get the Student from the service
        Student theStudent = studentService.findById(theId);

        // set Student as a model attribute to pre-populate the form
        theModel.addAttribute("student", theStudent);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String user=authentication.getName();

        theModel.addAttribute("user",user);

        // send over to our form
        return "studentsH/student-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("studentId") int theId) {

        // delete the Student
        studentService.deleteById(theId);

        // redirect to /Students/list
        return "redirect:/student/list";

    }

}