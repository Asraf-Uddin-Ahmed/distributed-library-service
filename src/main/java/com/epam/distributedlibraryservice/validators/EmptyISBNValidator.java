package com.epam.distributedlibraryservice.validators;

import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmptyISBNValidator implements ConstraintValidator<EmptyISBN, String> {


    @Override
    public void initialize(EmptyISBN constraintAnnotation) {
        // Initialization logic, if any
    }

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        // Validate if the ISBN is not empty or null and if it already exists
        if (Strings.isNotEmpty(isbn)) {
            return isValidIsbn10(isbn) || isValidIsbn13(isbn);
        }
        return true;
    }

    private boolean isValidIsbn10(String isbn) {
        String regex = "^(?:\\d{9}[\\d|Xx])|(?:\\d{1,5}-\\d{1,7}-\\d{1,6}-[\\d|Xx])$";
        return Pattern.matches(regex, isbn);
    }

    private boolean isValidIsbn13(String isbn) {
        String regex = "^(?:\\d{12}\\d|[\\d|-]{1,5}-\\d{1,7}-\\d{1,6}-\\d)$";
        return Pattern.matches(regex, isbn);
    }

}
