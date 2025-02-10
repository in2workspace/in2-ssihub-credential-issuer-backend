package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeferredCredentialRequest(
        @JsonProperty("transaction_id") String transactionId
) {
}
