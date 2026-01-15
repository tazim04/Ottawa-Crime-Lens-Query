package com.crimelens.crimelens_query.repository.projection;

import java.time.LocalDate;
import org.locationtech.jts.geom.Geometry;

public record GridStatProjection(
    Long id,

    // Geometry of the grid cell
    Geometry grid,

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
