package com.crimelens.crimelens_query.controller;

import com.crimelens.crimelens_query.dto.request.GridStatsRequest;
import com.crimelens.crimelens_query.dto.response.GridStatDTO;
import com.crimelens.crimelens_query.service.GridStatsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/crime")
@Slf4j
public class GridStatsController {

  private final GridStatsService service;

  // Fetch grid stats for a specific crime point
  @GetMapping("/grid/stats")
  public GridStatDTO getGridStats(@Valid @ModelAttribute GridStatsRequest request) {
    log.info("Running /grid/stats");
    return service.getGridStatsForPoint(request.lon(), request.lat()).orElse(GridStatDTO.EMPTY);
  }

  // Fetch grid stats for a specific grid id
  @GetMapping("/grid/{id}/stats")
  public GridStatDTO getGridStats(@PathVariable long id) {
    log.info("Running /grid/{}/stats", id);
    return service.getGridStatsById(id).orElse(GridStatDTO.EMPTY);
  }
}
