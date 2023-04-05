package com.epam.distributedlibraryservice.repositories;

import com.epam.distributedlibraryservice.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

}
