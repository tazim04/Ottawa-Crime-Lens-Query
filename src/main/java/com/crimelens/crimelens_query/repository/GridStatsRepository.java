package com.crimelens.crimelens_query.repository;

import com.crimelens.crimelens_query.repository.projection.GridCellMapProjection;
import com.crimelens.crimelens_query.repository.projection.GridStatProjection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GridStatsRepository {

  private final JdbcTemplate jdbc;

  private static final RowMapper<GridStatProjection> GRID_STAT_MAPPER =
      (rs, rowNum) ->
          new GridStatProjection(
              rs.getLong("id"),
              rs.getLong("total_crimes"),
              rs.getDouble("avg_crimes_per_year"),
              rs.getLong("crimes_last_year"),
              rs.getLong("crimes_last_5_years"),
              rs.getLong("crimes_last_10_years"),
              rs.getString("most_common_crime_all_time"),
              rs.getString("most_common_crime_last_year"),
              rs.getString("most_common_crime_last_5_years"),
              rs.getString("most_common_crime_last_10_years"),
              rs.getDate("first_reported").toLocalDate(),
              rs.getDate("last_reported").toLocalDate());

  private static final RowMapper<GridCellMapProjection> GRID_CELL_MAP_ROW_MAPPER =
      (rs, rowNum) ->
          new GridCellMapProjection(
              rs.getLong("id"),
              rs.getDouble("lon"),
              rs.getDouble("lat"),
              rs.getLong("total_crimes"));

  public Optional<GridStatProjection> getGridStatsForPoint(double lon, double lat) {
    String sql =
        """
            SELECT
                id,
                total_crimes,
                avg_crimes_per_year,
                crimes_last_year,
                crimes_last_5_years,
                crimes_last_10_years,
                most_common_crime_all_time,
                most_common_crime_last_year,
                most_common_crime_last_5_years,
                most_common_crime_last_10_years,
                first_reported,
                last_reported
            FROM crime_stats_grid
            WHERE grid = ST_SnapToGrid(
                ST_SetSRID(ST_MakePoint(?, ?), 4326),
                0.01
            )
            LIMIT 1
            """;

    return jdbc.query(sql, GRID_STAT_MAPPER, lon, lat).stream().findFirst();
  }

  public List<GridCellMapProjection> findGridCellsForViewport(
      double minLon, double minLat, double maxLon, double maxLat) {
    String sql =
        """
      SELECT
          id,
          ST_X(grid) AS lon,
          ST_Y(grid) AS lat,
          total_crimes
      FROM crime_stats_grid
      WHERE grid && ST_MakeEnvelope(?, ?, ?, ?, 4326)
      """;

    return jdbc.query(sql, GRID_CELL_MAP_ROW_MAPPER, minLon, minLat, maxLon, maxLat);
  }

  public Optional<GridStatProjection> getGridStatsById(long id) {
    String sql =
        """
                SELECT
                    id,
                    total_crimes,
                    avg_crimes_per_year,
                    crimes_last_year,
                    crimes_last_5_years,
                    crimes_last_10_years,
                    most_common_crime_all_time,
                    most_common_crime_last_year,
                    most_common_crime_last_5_years,
                    most_common_crime_last_10_years,
                    first_reported,
                    last_reported
                FROM crime_stats_grid
                WHERE id = ?
                LIMIT 1
                """;

    return jdbc.query(sql, GRID_STAT_MAPPER, id).stream().findFirst();
  }
}
