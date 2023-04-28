package com.gl.lab.studentmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.lab.studentmanagement.Exception.StudentException;
import com.gl.lab.studentmanagement.entity.Student;
import com.gl.lab.studentmanagement.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int theId) throws StudentException {
        Optional<Student> studentOptional=studentRepository.findById(theId);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }

        throw new StudentException("Student Details not present");
    }

    @Override
    @Transactional
    public Student save(Student thestudent) {
        return studentRepository.save(thestudent);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        studentRepository.deleteById(theId);
    }
}
