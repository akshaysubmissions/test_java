package com.oorja.oorjaTest.service;

import com.oorja.oorjaTest.model.RegisteredUsers;
import com.oorja.oorjaTest.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public RegisteredUsers findById(int id) {
        Optional<RegisteredUsers> users =  userDao.findById(id);
        return users.get();
    }

    @Override
    public boolean existsById(int id) {
        return userDao.existsById(id);
    }
}
