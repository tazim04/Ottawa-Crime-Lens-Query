package com.crimelens.crimelens_query.service;

import com.crimelens.crimelens_query.dto.response.GridCellDTO;
import com.crimelens.crimelens_query.dto.response.GridStatDTO;
import com.crimelens.crimelens_query.repository.GridStatsRepository;
import com.crimelens.crimelens_query.repository.projection.GridCellMapProjection;
import com.crimelens.crimelens_query.repository.projection.GridStatProjection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GridStatsService {

  private final GridStatsRepository gridStatsRepository;

  public Optional<GridStatDTO> getGridStatsForPoint(double lon, double lat) {
    return gridStatsRepository.getGridStatsForPoint(lon, lat).map(this::toDto);
  }

  @Cacheable(value = "gridStats", key = "'grid:' + #id")
  public Optional<GridStatDTO> getGridStatsById(long id) {
    log.info("[GridStatsService] id = {}", id);
    return gridStatsRepository.getGridStatsById(id).map(this::toDto);
  }

  @Cacheable(
      value = "gridViewport",
      key =
          "'z:' + #zoom + "
              + "':bbox:' + "
              + "T(java.lang.Math).floor(#minLon * 100) + ':' + "
              + "T(java.lang.Math).floor(#minLat * 100) + ':' + "
              + "T(java.lang.Math).floor(#maxLon * 100) + ':' + "
              + "T(java.lang.Math).floor(#maxLat * 100)")
  public List<GridCellDTO> getGridCellsForViewport(
      double minLon, double minLat, double maxLon, double maxLat, int zoom) {

    return gridStatsRepository.findGridCellsForViewport(minLon, minLat, maxLon, maxLat).stream()
        .map(p -> new GridCellDTO(p.id(), p.lon(), p.lat(), p.crimeCount()))
        .toList();
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

  private GridCellDTO toDto(GridCellMapProjection projection) {
    return new GridCellDTO(
        projection.id(), projection.lon(), projection.lat(), projection.crimeCount());
  }
}
