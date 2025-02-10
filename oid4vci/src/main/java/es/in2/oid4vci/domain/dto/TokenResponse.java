package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record TokenResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("token_type") String tokenType,
        @JsonProperty("expires_in") int expiresIn,
        @JsonProperty("authorization_details") List<AuthorizationDetail> authorizationDetails
) {

    @Builder
    public record AuthorizationDetail(
            @JsonProperty("type") String type,
            @JsonProperty("credential_configuration_id") String credentialConfigurationId,
            @JsonProperty("credential_identifiers") List<String> credentialIdentifiers
    ) {
    }

}

