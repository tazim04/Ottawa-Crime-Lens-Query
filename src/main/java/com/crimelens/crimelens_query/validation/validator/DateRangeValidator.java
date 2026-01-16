package com.crimelens.crimelens_query.validation.validator;

import com.crimelens.crimelens_query.dto.request.CrimeMapPointRequest;
import com.crimelens.crimelens_query.validation.annotation.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator
    implements ConstraintValidator<ValidDateRange, CrimeMapPointRequest> {

  @Override
  public boolean isValid(CrimeMapPointRequest r, ConstraintValidatorContext ctx) {
    if (r.startDate() == null || r.endDate() == null) {
      return true;
    }
    return !r.startDate().isAfter(r.endDate());
  }
}
