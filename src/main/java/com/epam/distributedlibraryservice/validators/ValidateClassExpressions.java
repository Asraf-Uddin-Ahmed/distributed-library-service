package com.epam.distributedlibraryservice.validators;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateClassExpressions {
	ValidateClassExpression[] value();
}