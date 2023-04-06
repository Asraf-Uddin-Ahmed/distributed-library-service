package com.epam.distributedlibraryservice.controllers;

import com.epam.distributedlibraryservice.dtos.home.UserMapper;
import com.epam.distributedlibraryservice.dtos.user.UserResponseDto;
import com.epam.distributedlibraryservice.entities.Book;
import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.services.BookService;
import com.epam.distributedlibraryservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users/{emailOrUsername}")
    public ResponseEntity<UserResponseDto> getUserByEmailOrUsername(@PathVariable("emailOrUsername") String emailOrUsername) {
        User user = userService.findByUsername(emailOrUsername);
        if (user == null) {
            user = userService.getFirstByEmail(emailOrUsername);
        }
        if (user != null && !user.getId().equals(userService.getCurrentUser().getId())) {
            return ResponseEntity.ok(userMapper.getUserResponseDto(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
