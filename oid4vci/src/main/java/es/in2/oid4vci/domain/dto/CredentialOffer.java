package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record CredentialOffer(
        @JsonProperty("credential_issuer") String credentialIssuer,
        @JsonProperty("credential_configuration_ids") List<String> credentialConfigurationIds,
        @JsonProperty("grants") Map<String, Grant> grants
) {

    @Builder
    public record Grant(
            @JsonProperty("pre-authorized_code") String preAuthorizedCode,
            @JsonProperty("tx_code") TransactionCode txCode
    ) {

        @Builder
        public record TransactionCode(
                @JsonProperty("input_mode") String inputMode,
                @JsonProperty("length") Integer length,
                @JsonProperty("description") String description
        ) {
        }

    }

}
