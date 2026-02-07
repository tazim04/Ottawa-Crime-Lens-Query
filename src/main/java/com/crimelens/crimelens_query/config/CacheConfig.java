package com.crimelens.crimelens_query.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

  @Bean
  public CaffeineCacheManager cacheManager() {
    CaffeineCacheManager manager = new CaffeineCacheManager("gridViewport", "crimeMapPoints", "gridStats");
    manager.setCaffeine(
        Caffeine.newBuilder()
            .maximumSize(20_000)
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .recordStats() // record caching stats
        );
    return manager;
  }
}
