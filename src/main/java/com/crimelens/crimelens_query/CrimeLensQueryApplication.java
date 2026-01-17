package com.crimelens.crimelens_query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CrimeLensQueryApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrimeLensQueryApplication.class, args);
  }
}
