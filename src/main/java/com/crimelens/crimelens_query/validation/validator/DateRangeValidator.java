package com.crimelens.crimelens_query.validation.validator;

import com.crimelens.crimelens_query.dto.request.MapCrimeRequest;
import com.crimelens.crimelens_query.validation.annotation.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, MapCrimeRequest> {

  @Override
  public boolean isValid(MapCrimeRequest r, ConstraintValidatorContext ctx) {
    if (r.startDate() == null || r.endDate() == null) {
      return true;
    }
    return !r.startDate().isAfter(r.endDate());
  }
}
