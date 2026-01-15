package com.crimelens.crimelens_query.service;

import com.crimelens.crimelens_query.dto.response.GridStatDTO;
import com.crimelens.crimelens_query.repository.GridStatsRepository;
import com.crimelens.crimelens_query.repository.projection.GridStatProjection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GridStatsService {

  private final GridStatsRepository gridStatsRepository;

  //  public List<GridStatDTO> getGridStats(
  //      double minLng, double minLat, double maxLng, double maxLat) {
  //    return gridStatsRepository.findGridStatsInBoundingBox(minLng, minLat, maxLng,
  // maxLat).stream()
  //        .map(this::toDto)
  //        .toList();
  //  }

  public Optional<GridStatDTO> getGridStatsForPoint(double lon, double lat) {
    return gridStatsRepository.getGridStatsForPoint(lon, lat).map(this::toDto);
  }

  private GridStatDTO toDto(GridStatProjection projection) {
    return new GridStatDTO(
        projection.id(),
        projection.totalCrimes(),
        projection.avgCrimesPerYear(),
        projection.crimesLastYear(),
        projection.crimesLast5Years(),
        projection.crimesLast10Years(),
        projection.mostCommonCrimeAllTime(),
        projection.mostCommonCrimeLastYear(),
        projection.mostCommonCrimeLast5Years(),
        projection.mostCommonCrimeLast10Years(),
        projection.firstReported(),
        projection.lastReported(),
        false);
  }
}
