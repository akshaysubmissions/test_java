package com.oorja.oorjaTest.repository;

import com.oorja.oorjaTest.model.RegisteredUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<RegisteredUsers, Integer> {

}
