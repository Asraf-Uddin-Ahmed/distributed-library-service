package com.epam.distributedlibraryservice.services;

import com.epam.distributedlibraryservice.entities.User;
import com.epam.distributedlibraryservice.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
class AppUserDetailsServiceTest {

    @InjectMocks
    private AppUserDetailsService appUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    void whenLoadByUserName_thenUserShouldBeFound() {
        // Arrange
        String username = "asraf";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        when(userRepository.findByUsername(username)).thenReturn(user);

        // Act
        UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    void whenLoadByUserNameNotExists_thenUserShouldThrowUsernameNotFoundException() {
        // Arrange
        String username = "asraf";
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Act and Assert
        assertThatThrownBy(() -> appUserDetailsService.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
