package com.rodkot.security.backend.entity.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypeDocument {
    @JsonProperty("audio")
    AUDIO,
    @JsonProperty("pdf")
    PDF;
}
