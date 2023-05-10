package com.oorja.oorjaTest.service;

import com.oorja.oorjaTest.model.RegisteredUsers;

import java.util.Optional;

public interface UserService {

    public RegisteredUsers findById(int id);
    public boolean existsById(int id);
}
