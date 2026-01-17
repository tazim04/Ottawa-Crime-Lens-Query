package com.crimelens.crimelens_query.repository.projection;

// For zoomed out UI
public record GridCellMapProjection(Long id, double lon, double lat, long crimeCount) {}
