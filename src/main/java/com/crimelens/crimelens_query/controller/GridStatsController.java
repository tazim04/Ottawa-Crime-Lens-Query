package com.crimelens.crimelens_query.controller;

import com.crimelens.crimelens_query.dto.request.GridStatsRequest;
import com.crimelens.crimelens_query.dto.response.GridStatDTO;
import com.crimelens.crimelens_query.service.GridStatsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/crime")
@Slf4j
public class GridStatsController {

  private final GridStatsService service;

  @GetMapping("/grid-stats")
  public GridStatDTO getGridStats(@Valid @ModelAttribute GridStatsRequest request) {
    log.info("Running /get-grid-stats");
    return service
        .getGridStatsForPoint(request.lon(), request.lat())
        .orElse(GridStatDTO.empty(request.lon(), request.lat()));
  }
}
