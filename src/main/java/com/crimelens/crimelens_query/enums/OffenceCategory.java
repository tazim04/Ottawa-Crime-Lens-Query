package com.crimelens.crimelens_query.enums;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum OffenceCategory {
  ARSON("Arson"),
  ASSAULTS("Assaults"),
  ATTEMPTED_MURDER("Attempted Murder"),
  BREAK_AND_ENTER("Break and Enter"),
  CRIMINAL_HARASSMENT("Criminal Harassment"),
  HOMICIDE("Homicide"),
  INDECENT_OR_HARASSING_COMMUNICATIONS("Indecent or Harassing Communications"),
  MISCHIEF("Mischief"),
  ROBBERY("Robbery"),
  THEFT_5000_AND_UNDER("Theft $5000 and Under"),
  THEFT_OVER_5000("Theft Over $5000"),
  THEFT_OF_MOTOR_VEHICLE("Theft of Motor Vehicle"),
  UTTERING_THREATS("Uttering Threats");

  private final String displayName;

  OffenceCategory(String displayName) {
    this.displayName = displayName;
  }

  // Convert from String -> OffenceCategory
  public static OffenceCategory fromString(String value) {
    return Arrays.stream(values())
        .filter(c -> c.name().equalsIgnoreCase(value) || c.displayName.equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown offence category: " + value));
  }
}
