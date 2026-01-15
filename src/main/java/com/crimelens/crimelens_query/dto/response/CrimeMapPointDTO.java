package com.crimelens.crimelens_query.dto.response;

import java.time.LocalDate;

public record CrimeMapPointDTO(
    Long id, String category, double lat, double lon, LocalDate occurredDate) {}
