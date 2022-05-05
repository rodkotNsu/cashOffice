package com.rodkot.security.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypeOperation {
    @JsonProperty("withdrawals")
    WITHDRAWALS,
    @JsonProperty("deposits")
    DEPOSITS;
}
