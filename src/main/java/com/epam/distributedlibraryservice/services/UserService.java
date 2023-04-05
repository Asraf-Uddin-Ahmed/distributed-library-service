package com.epam.distributedlibraryservice.services;

import com.epam.distributedlibraryservice.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
