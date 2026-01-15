package com.crimelens.crimelens_query.service;

import org.springframework.stereotype.Service;

@Service
public class ZoomPolicyService {

  // Ensure user is zoomed in enough to safely request crime points
  //  public void assertCrimePointsAllowed(int zoom) {
  //    if (zoom < 14) {
  //      throw new IllegalArgumentException("Zoom in further to view individual crime points");
  //    }
  //  }

  // Hard cap on how many crime points can be returned for a single map request
  public int maxCrimePoints(int zoom) {
    if (zoom >= 16) return 2000;
    if (zoom >= 14) return 800;
    if (zoom >= 12) return 300;
    if (zoom >= 10) return 100;
    return 50;
  }
}
