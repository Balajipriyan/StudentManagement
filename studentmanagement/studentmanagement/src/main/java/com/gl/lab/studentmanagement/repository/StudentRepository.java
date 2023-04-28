package com.gl.lab.studentmanagement.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.lab.studentmanagement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
