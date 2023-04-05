package com.epam.distributedlibraryservice.validators;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Repeatable(ValidateClassExpressions.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { SpELClassValidator.class })
@Documented
public @interface ValidateClassExpression {

	String message() default "{expression.validation.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value();

	String[] dependentFields() default {};

	String[] appliedFields();

	String actionMessage();

}
