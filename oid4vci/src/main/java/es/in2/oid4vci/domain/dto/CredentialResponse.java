package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record CredentialResponse(
        @JsonProperty("credentials") List<Credential> credentials,
        @JsonProperty("transaction_id") String transactionId
) {

    @Builder
    public record Credential(
            @JsonProperty("credential") String credential
    ) {
    }

}
