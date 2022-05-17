package com.springproject.demo.dao;

import com.springproject.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;




public interface StudentRepository extends JpaRepository<Student, Integer> {



}
