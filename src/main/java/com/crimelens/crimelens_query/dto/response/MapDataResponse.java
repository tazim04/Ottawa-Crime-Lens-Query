package com.crimelens.crimelens_query.dto.response;

import com.crimelens.crimelens_query.enums.MapDataType;
import java.util.List;

public record MapDataResponse(MapDataType type, List<?> data) {
  public static MapDataResponse grid(List<GridCellDTO> grids) {
    return new MapDataResponse(MapDataType.GRID, grids);
  }

  public static MapDataResponse points(List<CrimeMapPointDTO> points) {
    return new MapDataResponse(MapDataType.POINTS, points);
  }
}
