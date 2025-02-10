package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CredentialDeferredResponse(
        @JsonProperty("transaction_id") String transactionId
) implements CredentialResponse {
}
