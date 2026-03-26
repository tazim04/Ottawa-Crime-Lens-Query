package com.crimelens.crimelens_query.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GridAnomalyProjection(
    LocalDate date,
    BigDecimal anomalyScore,
    String modelVersion,
    BigDecimal triagePercentile,
    String triageLabel,
    String triageExplanation) {}
