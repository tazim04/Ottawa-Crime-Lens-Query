package com.crimelens.crimelens_query.dto.request;

import com.crimelens.crimelens_query.validation.annotation.ValidBoundingBox;
import com.crimelens.crimelens_query.validation.annotation.ValidDateRange;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// Used for when frontend map needs to load crime points
@ValidBoundingBox
@ValidDateRange
public record CrimeMapPointRequest(
    @NotNull double minLon,
    @NotNull double minLat,
    @NotNull double maxLon,
    @NotNull double maxLat,
    @Min(0) int zoom,
    LocalDate startDate,
    LocalDate endDate) {}
