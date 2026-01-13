package com.crimelens.crimelens_query.repository;

import com.crimelens.crimelens_query.repository.projection.GridStatProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GridStatsRepository {

    @Query(value = """
        SELECT
            grid,
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
        WHERE grid && ST_MakeEnvelope(
            :minLng, :minLat,
            :maxLng, :maxLat,
            4326
        )
    """, nativeQuery = true)
    List<GridStatProjection> findGridStatsInBoundingBox(
            @Param("minLng") double minLng,
            @Param("minLat") double minLat,
            @Param("maxLng") double maxLng,
            @Param("maxLat") double maxLat
    );
}
