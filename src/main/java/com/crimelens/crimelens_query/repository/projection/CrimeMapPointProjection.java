package com.crimelens.crimelens_query.repository.projection;

import java.time.LocalDateTime;
import org.locationtech.jts.geom.Point;

public interface CrimeMapPointProjection {
  Long getId();

  String getOffenceCategory();

  LocalDateTime getOccurredDate();

  Point getLocation();
}
