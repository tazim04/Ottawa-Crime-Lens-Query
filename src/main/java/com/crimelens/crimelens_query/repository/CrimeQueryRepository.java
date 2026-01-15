package com.crimelens.crimelens_query.repository;

import com.crimelens.crimelens_query.enums.CrimeSource;
import com.crimelens.crimelens_query.repository.projection.CrimeDetailProjection;
import com.crimelens.crimelens_query.repository.projection.CrimeMapPointProjection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CrimeQueryRepository {

  private final JdbcTemplate jdbc;

  private static final RowMapper<CrimeMapPointProjection> MAP_POINT_MAPPER =
      (rs, rowNum) ->
          new CrimeMapPointProjection(
              rs.getLong("id"),
              rs.getString("offenceCategory"),
              rs.getDate("occurredDate").toLocalDate(),
              rs.getDouble("lon"),
              rs.getDouble("lat"));

  public List<CrimeMapPointProjection> findMapPoints(
      double minLon,
      double minLat,
      double maxLon,
      double maxLat,
      LocalDate start,
      LocalDate end,
      int limit) {

    String sql =
        """
    SELECT id,
           offence_category AS offenceCategory,
           occurred_date AS occurredDate,
           ST_X(location) AS lon,
           ST_Y(location) AS lat
    FROM crime_records
    WHERE location && ST_MakeEnvelope(?, ?, ?, ?, 4326)
      AND reported_date BETWEEN ? AND ?
    LIMIT ?
  """;

    return jdbc.query(sql, MAP_POINT_MAPPER, minLon, minLat, maxLon, maxLat, start, end, limit);
  }

  public Optional<CrimeDetailProjection> findCrimeDetail(Long id) {

    String sql =
        """
    SELECT id, go_number,
           occurred_date, occurred_hour,
           reported_date, reported_hour,
           offence_summary, offence_category,
           neighbourhood, intersection, source
    FROM crime_records
    WHERE id = ?
  """;

    return jdbc.query(
        sql,
        rs -> {
          if (!rs.next()) return Optional.empty();

          return Optional.of(
              new CrimeDetailProjection(
                  rs.getLong("id"),
                  rs.getString("go_number"),
                  rs.getString("offence_summary"),
                  rs.getString("offence_category"),
                  rs.getString("neighbourhood"),
                  rs.getString("intersection"),
                  rs.getDate("occurred_date").toLocalDate(),
                  rs.getInt("occurred_hour"),
                  rs.getDate("reported_date").toLocalDate(),
                  rs.getInt("reported_hour"),
                  CrimeSource.valueOf(rs.getString("source").toUpperCase())));
        },
        id);
  }
}
