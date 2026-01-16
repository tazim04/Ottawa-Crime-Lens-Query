package com.crimelens.crimelens_query.service;

import com.crimelens.crimelens_query.dto.request.CrimeMapPointRequest;
import com.crimelens.crimelens_query.dto.response.MapDataResponse;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MapQueryService {

  private final CrimeQueryService crimeQueryService;
  private final GridStatsService gridStatsService;
  private final ZoomPolicyService zoomPolicyService;

  public MapDataResponse getMapData(
      double minLon,
      double minLat,
      double maxLon,
      double maxLat,
      LocalDate startDate,
      LocalDate endDate,
      int zoom) {

    if (zoomPolicyService.usePrecomputedGrid(zoom)) {
      log.info("Returning precomputed grid.");
      return MapDataResponse.grid(
          gridStatsService.getGridCellsForViewport(minLon, minLat, maxLon, maxLat, zoom));
    }

    log.info("Returning crime points.");
    return MapDataResponse.points(
        crimeQueryService.getCrimeMapPoints(
            new CrimeMapPointRequest(minLon, minLat, maxLon, maxLat, zoom, startDate, endDate)));
  }
}
