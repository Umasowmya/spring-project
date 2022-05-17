package com.springproject.demo.entity;


import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="book")
public class Book {

    // define our fields

    // define constructors

    // define getter setters

    // define tostring

    // annotate fields


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    @NotBlank(message="is required")
    private String name;


    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name="book_student",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns=@JoinColumn(name="student_id") )
    private List<Student> students;


    public Book(){}

    public Book(String name){
        this.name=name;
    }


    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + name + "]";
    }


    public void addStudent(Student student){
        if(students==null)
            students=new ArrayList<>();
        students.add(student);

    }



}