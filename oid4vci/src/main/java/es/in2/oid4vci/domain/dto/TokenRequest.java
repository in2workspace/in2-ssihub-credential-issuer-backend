package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenRequest(
        @JsonProperty("grant_type") String grantType,
        @JsonProperty("pre-authorized_code") String preAuthorizedCode,
        @JsonProperty("tx_code") String txCode
) {
}

