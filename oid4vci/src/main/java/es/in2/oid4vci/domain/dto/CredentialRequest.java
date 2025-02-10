package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record CredentialRequest(
        @JsonProperty("credential_identifier") String credentialIdentifier,
        @JsonProperty("credential_configuration_id") String credentialConfigurationId,
        @JsonProperty("proof") Proof proof,
        @JsonProperty("proofs") Map<String, String[]> proofs,
        @JsonProperty("credential_response_encryption") EncryptionParameters encryptionParameters
) {

    public record Proof(
            @JsonProperty("proof_type") String proofType,
            @JsonProperty("jwt") String jwt
    ) {
    }

    public record EncryptionParameters(
            @JsonProperty("jwk") Map<String, Object> jwk,
            @JsonProperty("alg") String alg,
            @JsonProperty("enc") String enc
    ) {
    }
}

