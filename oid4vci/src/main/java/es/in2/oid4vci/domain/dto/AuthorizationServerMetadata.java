package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AuthorizationServerMetadata(
        @JsonProperty("issuer") String issuer,
        @JsonProperty("authorization_endpoint") String authorizationEndpoint,
        @JsonProperty("token_endpoint") String tokenEndpoint,
        @JsonProperty("jwks_uri") String jwksUri,
        @JsonProperty("grant_types_supported") List<String> grantTypesSupported,
        @JsonProperty("response_types_supported") List<String> responseTypesSupported,
        @JsonProperty("pre-authorized_grant_anonymous_access_supported") boolean preAuthorizedGrantAnonymousAccessSupported
) {
}

