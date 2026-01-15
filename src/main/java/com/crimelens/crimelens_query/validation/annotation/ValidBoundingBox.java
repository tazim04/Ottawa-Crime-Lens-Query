package com.crimelens.crimelens_query.validation.annotation;

import com.crimelens.crimelens_query.validation.validator.BoundingBoxValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BoundingBoxValidator.class)
public @interface ValidBoundingBox {
  String message() default "Invalid bounding box";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
