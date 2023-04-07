package com.epam.distributedlibraryservice.configs;

import com.epam.distributedlibraryservice.dtos.home.UserMapper;
import com.epam.distributedlibraryservice.dtos.home.UserRequestDto;
import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.models.AppUserDetailsModel;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpSession session;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if (authentication.getPrincipal() instanceof DefaultOAuth2User userDetails) {
            String username = userDetails.getAttribute("email") != null ? userDetails.getAttribute("email") : userDetails.getAttribute("login") + "@gmail.com";
            User user = userService.findByUsername(username);
            // If such user does not exist, create a new one
            if (user == null) {
                UserRequestDto requestDto = new UserRequestDto();
                requestDto.setEmail(username);
                requestDto.setUsername(username);
                requestDto.setPassword(username);
                user = userMapper.getUserEntity(requestDto);
                userService.save(user);
            }
            session.setAttribute("CURRENT_USER_ID", user.getId());
        } else if (authentication.getPrincipal() instanceof AppUserDetailsModel appUserDetailsModel) {
            String username = appUserDetailsModel.getUsername();
            User user = userService.findByUsername(username);
            session.setAttribute("CURRENT_USER_ID", user.getId());
        }
        String redirectUrl = "/dashboard";
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
