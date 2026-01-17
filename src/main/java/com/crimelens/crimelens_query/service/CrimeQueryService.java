package com.crimelens.crimelens_query.service;

import com.crimelens.crimelens_query.dto.request.CrimeMapPointRequest;
import com.crimelens.crimelens_query.dto.response.CrimeDetailDTO;
import com.crimelens.crimelens_query.dto.response.CrimeMapPointDTO;
import com.crimelens.crimelens_query.repository.CrimeQueryRepository;
import com.crimelens.crimelens_query.repository.projection.CrimeDetailProjection;
import com.crimelens.crimelens_query.repository.projection.CrimeMapPointProjection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrimeQueryService {

  private final CrimeQueryRepository crimeQueryRepository;
  private final ZoomPolicyService zoomPolicyService;

  // Return crime markers for map viewport
  public List<CrimeMapPointDTO> getCrimeMapPoints(CrimeMapPointRequest r) {

    // query db and return results
    return crimeQueryRepository
        .findMapPoints(r.minLon(), r.minLat(), r.maxLon(), r.maxLat(), r.startDate(), r.endDate())
        .stream()
        .map(this::toMapPointDto)
        .toList();
  }

  public CrimeDetailDTO getCrimeDetail(long crimeId) {
    return crimeQueryRepository
        .findCrimeDetail(crimeId)
        .map(this::toDetailDto)
        .orElseThrow(() -> new IllegalArgumentException("Crime not found"));
  }

  // ---------------- mapping ----------------

  private CrimeMapPointDTO toMapPointDto(CrimeMapPointProjection p) {

    return new CrimeMapPointDTO(p.id(), p.offenceCategory(), p.lat(), p.lon(), p.occurredDate());
  }

  private CrimeDetailDTO toDetailDto(CrimeDetailProjection p) {
    return new CrimeDetailDTO(
        p.id(),
        p.goNumber(),
        p.offenceSummary(),
        p.offenceCategory(),
        p.neighbourhood(),
        p.intersection(),
        p.occurredDate(),
        p.occurredHour(),
        p.reportedDate(),
        p.reportedHour(),
        p.source());
  }
}
