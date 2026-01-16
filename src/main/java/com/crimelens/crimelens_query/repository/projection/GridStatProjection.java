package com.crimelens.crimelens_query.repository.projection;

import java.time.LocalDate;

public record GridStatProjection(
    Long id,

    // Counts
    Long totalCrimes,
    Double avgCrimesPerYear,
    Long crimesLastYear,
    Long crimesLast5Years,
    Long crimesLast10Years,

    // Modes
    String mostCommonCrimeAllTime,
    String mostCommonCrimeLastYear,
    String mostCommonCrimeLast5Years,
    String mostCommonCrimeLast10Years,

    // Metadata
    LocalDate firstReported,
    LocalDate lastReported) {}
