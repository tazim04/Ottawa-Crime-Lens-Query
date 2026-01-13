package com.crimelens.crimelens_query.repository.projection;

import org.locationtech.jts.geom.Geometry;

import java.time.LocalDateTime;

public interface GridStatProjection {

    // Geometry of the grid cell
    Geometry getGrid();

    // Counts
    Long getTotalCrimes();
    Double getAvgCrimesPerYear();

    Long getCrimesLastYear();
    Long getCrimesLast5Years();
    Long getCrimesLast10Years();

    // Modes
    String getMostCommonCrimeAllTime();
    String getMostCommonCrimeLastYear();
    String getMostCommonCrimeLast5Years();
    String getMostCommonCrimeLast10Years();

    // Metadata
    LocalDateTime getFirstReported();
    LocalDateTime getLastReported();
}
