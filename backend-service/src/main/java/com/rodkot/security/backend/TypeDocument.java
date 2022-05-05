package com.rodkot.security.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypeDocument {
    @JsonProperty("audio")
    AUDIO,
    @JsonProperty("pdf")
    PDF;
}
