package com.crimelens.crimelens_query.service;

import org.springframework.stereotype.Service;

@Service
public class ZoomPolicyService {

  public boolean usePrecomputedGrid(int zoom) {
    return zoom <= 11;
  }
}
