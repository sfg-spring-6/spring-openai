package org.chintanu.spring_openai.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record CapitalInfo(@JsonPropertyDescription("Name of the Capital") String name,
                          @JsonPropertyDescription("Population of the Capital") long population,
                          @JsonPropertyDescription("Land Area of the Capital") String area,
                          @JsonPropertyDescription("Primary Spoken Language") String language,
                          @JsonPropertyDescription("Dance form practiced") String danceForm) {
}
