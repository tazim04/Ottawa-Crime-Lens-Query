package com.crimelens.crimelens_query.dto.request;

import jakarta.validation.constraints.NotNull;

// request grid statistics
public record GridStatsRequest(@NotNull double lon, @NotNull double lat) {}
