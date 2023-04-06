package com.epam.distributedlibraryservice.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Locale;


public class SpELClassValidator implements ConstraintValidator<ValidateClassExpression, Object> {

	@Autowired
	private MessageSource messageSource;

	private ValidateClassExpression annotation;
	private ExpressionParser parser = new SpelExpressionParser();

	@Override
	public void initialize(ValidateClassExpression constraintAnnotation) {
		annotation = constraintAnnotation;
		parser.parseExpression(constraintAnnotation.value());
	}

	public boolean isValid(Object value, ConstraintValidatorContext context) {
		StandardEvaluationContext spelContext = new StandardEvaluationContext(value);
		boolean isValid = (Boolean) parser.parseExpression(annotation.value()).getValue(spelContext);
		if(!isValid) {
			// COPILOT has generated this awesome code
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(messageSource.getMessage(annotation.message(), null, Locale.getDefault()))
					.addPropertyNode(annotation.appliedFields()[0]).addConstraintViolation();
		}
		return isValid;
	}

}