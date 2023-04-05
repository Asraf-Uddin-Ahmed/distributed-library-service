package com.epam.distributedlibraryservice.controllers;

import com.epam.distributedlibraryservice.dtos.home.UserMapper;
import com.epam.distributedlibraryservice.dtos.home.UserRequestDto;
import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.services.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("requestDto", new UserRequestDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("requestDto") @Valid UserRequestDto requestDto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        User user = userMapper.getUserEntity(requestDto);
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
