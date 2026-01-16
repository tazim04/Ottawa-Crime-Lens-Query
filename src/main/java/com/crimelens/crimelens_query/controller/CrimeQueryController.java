package com.crimelens.crimelens_query.controller;

import com.crimelens.crimelens_query.dto.request.CrimeMapPointRequest;
import com.crimelens.crimelens_query.dto.response.CrimeDetailDTO;
import com.crimelens.crimelens_query.dto.response.CrimeMapPointDTO;
import com.crimelens.crimelens_query.service.CrimeQueryService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crime-query")
@RequiredArgsConstructor
@Slf4j
public class CrimeQueryController {

  private final CrimeQueryService service;

  @GetMapping("/map-points")
  public List<CrimeMapPointDTO> getCrimeMapPoints(@Valid CrimeMapPointRequest request) {
    log.info("Running /map-points!");
    return service.getCrimeMapPoints(request);
  }

  @GetMapping("/crime-details")
  public CrimeDetailDTO getCrimeDetails(@Valid long crimeId) {
    log.info("Running /crime-details!");
    return service.getCrimeDetail(crimeId);
  }
}
