package com.crimelens.crimelens_query.validation.validator;

import com.crimelens.crimelens_query.dto.request.MapCrimeRequest;
import com.crimelens.crimelens_query.validation.annotation.ValidBoundingBox;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoundingBoxValidator
    implements ConstraintValidator<ValidBoundingBox, MapCrimeRequest> {

  @Override
  public boolean isValid(MapCrimeRequest r, ConstraintValidatorContext ctx) {
    if (r == null) return true;

    double latSpan = r.maxLat() - r.minLat();
    double lonSpan = r.maxLon() - r.minLon();

    if (latSpan <= 0 || lonSpan <= 0) return false;

    // Absolute safety caps (Ottawa-sized max)
    return !(latSpan > 1.0) && !(lonSpan > 1.0);
  }
}
