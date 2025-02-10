package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CredentialErrorResponse(
        @JsonProperty("error") String error,
        @JsonProperty("error_description") String errorDescription
) implements CredentialResponse {
}

