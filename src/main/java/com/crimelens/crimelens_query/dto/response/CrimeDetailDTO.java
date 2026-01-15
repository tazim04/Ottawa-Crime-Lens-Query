package com.crimelens.crimelens_query.dto.response;

import com.crimelens.crimelens_query.enums.CrimeSource;
import java.time.LocalDate;

public record CrimeDetailDTO(
    Long id,
    String goNumber,
    String offenceSummary,
    String offenceCategory,
    String neighbourhood,
    String intersection,
    LocalDate occurredDate,
    Integer occurredHour,
    LocalDate reportedDate,
    Integer reportedHour,
    CrimeSource source) {}
