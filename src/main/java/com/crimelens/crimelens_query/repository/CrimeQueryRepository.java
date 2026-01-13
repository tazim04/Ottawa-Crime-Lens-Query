package com.crimelens.crimelens_query.repository;

import com.crimelens.crimelens_query.repository.projection.CrimeDetailProjection;
import com.crimelens.crimelens_query.repository.projection.CrimeMapPointProjection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeQueryRepository {

  @Query(
      value =
          """
        SELECT id,
               offence_category AS offenceCategory,
               occurred_date AS occurredDate,
               location
        FROM crime_records
        WHERE location && ST_MakeEnvelope(:minLng, :minLat, :maxLng, :maxLat, 4326)
          AND occurred_date BETWEEN :start AND :end
        LIMIT :limit
    """,
      nativeQuery = true)
  List<CrimeMapPointProjection> findMapPoints(
      @Param("minLng") double minLng,
      @Param("minLat") double minLat,
      @Param("maxLng") double maxLng,
      @Param("maxLat") double maxLat,
      @Param("start") LocalDateTime start,
      @Param("end") LocalDateTime end,
      @Param("limit") int limit);

  @Query(
      value =
          """
        SELECT id, go_number, 
               occurred_date, occured_hour,
               reported_date, reported_hour,
               offence_summary, offence_category,
               neighbourhood, intersection, source
        FROM crime_records
        WHERE id = :id
    """,
      nativeQuery = true)
  Optional<CrimeDetailProjection> findCrimeDetail(@Param("id") Long id);
}
