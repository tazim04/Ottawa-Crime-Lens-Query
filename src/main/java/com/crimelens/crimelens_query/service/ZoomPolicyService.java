package com.crimelens.crimelens_query.service;

import org.springframework.stereotype.Service;

@Service
public class ZoomPolicyService {

  // Hard cap on how many crime points can be returned for a single map request
  //  public int maxCrimePoints(int zoom) {
  //    if (zoom >= 17) return 4000;
  //    if (zoom >= 13) return 3000;
  //    if (zoom == 12) return 2000;
  //    return 1000; // force zoom-in
  //  }

  public boolean usePrecomputedGrid(int zoom) {
    return zoom <= 12;
  }
}
