package com.crimelens.crimelens_query.repository;

import com.crimelens.crimelens_query.repository.projection.GridAnomalyProjection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GridAnomalyRepository {

  private final JdbcTemplate jdbc;

  private static final RowMapper<GridAnomalyProjection> GRID_ANOMALY_MAPPER =
      (rs, rowNum) ->
          new GridAnomalyProjection(
              rs.getDate("date").toLocalDate(),
              rs.getBigDecimal("anomaly_score"),
              rs.getString("model_version"),
              rs.getBigDecimal("triage_percentile"),
              rs.getString("triage_label"),
              rs.getString("triage_explanation"));

  public Optional<GridAnomalyProjection> findByGridId(long gridId) {
    String sql =
        """
                SELECT
                    date,
                    anomaly_score,
                    model_version,
                    triage_percentile,
                    triage_label,
                    triage_explanation
                FROM crime_anomaly_scores
                WHERE grid_id = CAST(? AS text)
                """;

    return jdbc.query(sql, GRID_ANOMALY_MAPPER, gridId).stream().findFirst();
  }
}
