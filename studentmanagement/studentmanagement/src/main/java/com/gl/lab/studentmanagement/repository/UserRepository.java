package com.gl.lab.studentmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.lab.studentmanagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u where u.username = ?1")
    User getUserByUsername(String userName);

}
