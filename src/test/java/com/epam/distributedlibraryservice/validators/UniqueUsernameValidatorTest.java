package com.epam.distributedlibraryservice.validators;

import com.epam.distributedlibraryservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class UniqueUsernameValidatorTest {

    @Mock
    private UserService userService;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @InjectMocks
    private UniqueUsernameValidator uniqueUsernameValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testIsValid_withUniqueUsername_shouldReturnTrue() {
        // Arrange
        String username = "asraf";
        when(userService.findByUsername(username)).thenReturn(null);

        // Act
        boolean isValid = uniqueUsernameValidator.isValid(username, constraintValidatorContext);

        // Assert
        assertTrue(isValid);
        verify(userService, times(1)).findByUsername(username);
    }

}
