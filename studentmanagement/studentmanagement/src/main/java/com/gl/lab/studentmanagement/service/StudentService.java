package com.gl.lab.studentmanagement.service;



import org.springframework.stereotype.Service;

import com.gl.lab.studentmanagement.Exception.StudentException;
import com.gl.lab.studentmanagement.entity.Student;

import java.util.List;


public interface StudentService {

    public List<Student> findAll();

    public Student findById(int theId) throws StudentException;

    public Student save(Student thestudent);

    public void deleteById(int theId);

}