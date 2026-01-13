package com.crimelens.crimelens_query.dto;

import java.time.LocalDateTime;

public record CrimeMapPointDTO(
        Long id,
        String category,
        double lat,
        double lng,
        LocalDateTime occurredDate
) {}
