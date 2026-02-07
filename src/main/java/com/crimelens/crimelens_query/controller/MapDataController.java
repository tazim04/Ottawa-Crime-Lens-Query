package com.crimelens.crimelens_query.controller;

import com.crimelens.crimelens_query.dto.response.MapDataResponse;
import com.crimelens.crimelens_query.enums.OffenceCategory;
import com.crimelens.crimelens_query.service.MapQueryService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor
@Slf4j
public class MapDataController {
  private final MapQueryService mapQueryService;

  @GetMapping("/data")
  public MapDataResponse getMapData(
      @RequestParam double minLon,
      @RequestParam double minLat,
      @RequestParam double maxLon,
      @RequestParam double maxLat,
      @RequestParam int zoom,
      @RequestParam(required = false) LocalDate startDate,
      @RequestParam(required = false) LocalDate endDate,
      @RequestParam(required = false) String category) {

    OffenceCategory offenceCategory =
        category != null ? OffenceCategory.fromString(category) : null;
    return mapQueryService.getMapData(
        minLon, minLat, maxLon, maxLat, startDate, endDate, offenceCategory, zoom);
  }
}
