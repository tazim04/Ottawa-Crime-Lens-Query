package com.crimelens.crimelens_query.controller;

import com.crimelens.crimelens_query.dto.response.CrimeDetailDTO;
import com.crimelens.crimelens_query.service.CrimeQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crime")
@RequiredArgsConstructor
@Slf4j
public class CrimeQueryController {

  private final CrimeQueryService service;

  @GetMapping("/{crimeId}")
  public CrimeDetailDTO getCrimeDetails(@PathVariable long crimeId) {
    log.info("Running /crime-details!");
    return service.getCrimeDetail(crimeId);
  }
}
