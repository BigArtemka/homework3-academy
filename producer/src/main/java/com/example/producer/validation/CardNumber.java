package com.example.producer.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CardNameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNumber {
    String message() default "{com.example.producer.validation.CardName.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
