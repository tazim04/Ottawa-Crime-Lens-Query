package com.crimelens.crimelens_query.dto;

import com.crimelens.crimelens_query.enums.CrimeSource;

import java.time.LocalDateTime;

public record CrimeDetailDTO(
    Long id,
    String goNumber,
    String offenceSummary,
    String offenceCategory,
    String neighbourhood,
    String intersection,
    LocalDateTime occurredDate,
    LocalDateTime reportedDate,
    CrimeSource source
) {}
