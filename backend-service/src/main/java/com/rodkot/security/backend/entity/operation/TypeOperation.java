package com.rodkot.security.backend.entity.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypeOperation {
    @JsonProperty("WITHDRAWALS")
    WITHDRAWALS,
    @JsonProperty("DEPOSITS")
    DEPOSITS;
}
