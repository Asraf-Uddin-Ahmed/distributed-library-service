package com.epam.distributedlibraryservice.dtos.home;

import com.epam.distributedlibraryservice.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User getUserEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setName(userRequestDto.getUsername());
        user.setUserStatus("ACTIVE");
        return user;
    }
}
