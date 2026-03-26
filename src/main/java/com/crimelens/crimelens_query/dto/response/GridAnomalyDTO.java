package com.crimelens.crimelens_query.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GridAnomalyDTO(
    LocalDate date,
    BigDecimal anomalyScore,
    String modelVersion,
    BigDecimal triagePercentile,
    String triageLabel,
    String triageExplanation) {}
