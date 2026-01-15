package com.crimelens.crimelens_query.dto.response;

import java.time.LocalDate;

public record GridStatDTO(
    long id,
    long totalCrimes,
    double avgCrimesPerYear,
    long crimesLastYear,
    long crimesLast5Years,
    long crimesLast10Years,
    String mostCommonCrimeAllTime,
    String mostCommonCrimeLastYear,
    String mostCommonCrimeLast5Years,
    String mostCommonCrimeLast10Years,
    LocalDate firstReported,
    LocalDate lastReported,
    boolean empty) {
  public static GridStatDTO empty(double lon, double lat) {
    return new GridStatDTO(-1, 0, 0.0, 0, 0, 0, null, null, null, null, null, null, true);
  }
}
