package com.epam.distributedlibraryservice.services.impl;

import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.models.AppUserDetailsModel;
import com.epam.distributedlibraryservice.repositories.UserRepository;
import com.epam.distributedlibraryservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the principal, which represents the currently authenticated user
            Object principal = authentication.getPrincipal();
            String username = null;
            if (principal instanceof AppUserDetailsModel appUserDetailsModel) {
                username = appUserDetailsModel.getUsername();
            } else if (principal instanceof DefaultOidcUser defaultOidcUser) {
                username = defaultOidcUser.getEmail();
            }
            return userRepository.findByUsername(username);
        }
        throw new IllegalStateException("Cannot find the current user");
    }
}