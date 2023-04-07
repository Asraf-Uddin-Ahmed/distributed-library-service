package com.epam.distributedlibraryservice.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@SpringJUnitConfig
class EmptyISBNValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    private EmptyISBNValidator emptyISBNValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        emptyISBNValidator = new EmptyISBNValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456789X", "978-0-306-40615-7", ""})
    void testValidIsbn_forLength10AndLength13AndEmpty_shouldReturnTrue(String arg) {
        assertTrue(emptyISBNValidator.isValid(arg, constraintValidatorContext));
    }

    @Test
    void testIsValidWithNullIsbn() {
        String isbn = null;
        assertTrue(emptyISBNValidator.isValid(isbn, constraintValidatorContext));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456789", "123456789@", "12345678"})
    void testInvalidIsbn_forLengthAndFormat_shouldReturnFalse(String arg) {
        assertFalse(emptyISBNValidator.isValid(arg, constraintValidatorContext));
    }

}
