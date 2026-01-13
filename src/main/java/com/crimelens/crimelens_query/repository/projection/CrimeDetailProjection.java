package com.crimelens.crimelens_query.repository.projection;

import com.crimelens.crimelens_query.enums.CrimeSource;

import java.time.LocalDateTime;

public interface CrimeDetailProjection {
    Long getId();
    String getGoNumber();
    LocalDateTime getOccurredDate();
    LocalDateTime getReportedDate();
    String getOffenceSummary();
    String getOffenceCategory();
    String getNeighbourhood();
    String getIntersection();
    CrimeSource getSource();
}
