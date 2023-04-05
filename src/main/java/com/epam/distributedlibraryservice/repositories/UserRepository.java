package com.epam.distributedlibraryservice.repositories;

import com.epam.distributedlibraryservice.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
}
