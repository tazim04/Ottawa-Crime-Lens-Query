package com.crimelens.crimelens_query.repository.projection;

import java.time.LocalDate;

public record CrimeMapPointProjection(
    Long id, String offenceCategory, LocalDate occurredDate, double lon, double lat) {}
