package com.epam.distributedlibraryservice.configs;

import com.epam.distributedlibraryservice.dtos.home.UserMapper;
import com.epam.distributedlibraryservice.dtos.home.UserRequestDto;
import com.epam.distributedlibraryservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if (authentication.getPrincipal() instanceof DefaultOAuth2User userDetails) {
            String username = userDetails.getAttribute("email") != null ? userDetails.getAttribute("email") : userDetails.getAttribute("login") + "@gmail.com";
            if (userService.findByUsername(username) == null) {
                UserRequestDto requestDto = new UserRequestDto();
                requestDto.setEmail(username);
                requestDto.setUsername(username);
                requestDto.setPassword(username);
                userService.save(userMapper.getUserEntity(requestDto));
            }
        }
        String redirectUrl = "/";
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
