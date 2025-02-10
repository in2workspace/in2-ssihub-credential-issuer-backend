package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record CredentialIssuerMetadata(
        @JsonProperty("credential_issuer") String credentialIssuer,
        @JsonProperty("authorization_servers") List<String> authorizationServers,
        @JsonProperty("credential_endpoint") String credentialEndpoint,
        @JsonProperty("nonce_endpoint") String nonceEndpoint,
        @JsonProperty("deferred_credential_endpoint") String deferredCredentialEndpoint,
        @JsonProperty("notification_endpoint") String notificationEndpoint,
        @JsonProperty("credential_response_encryption") EncryptionSupport encryptionSupport,
        @JsonProperty("credential_configurations_supported") Map<String, CredentialConfiguration> credentialConfigurations
) {

    @Builder
    public record EncryptionSupport(
            @JsonProperty("alg_values_supported") List<String> algValuesSupported,
            @JsonProperty("enc_values_supported") List<String> encValuesSupported,
            @JsonProperty("encryption_required") boolean encryptionRequired
    ) {
    }

    @Builder
    public record CredentialConfiguration(
            @JsonProperty("format") String format,
            @JsonProperty("cryptographic_binding_methods_supported") List<String> cryptographicBindingMethods,
            @JsonProperty("credential_signing_alg_values_supported") List<String> credentialSigningAlgValues,
            @JsonProperty("display") List<DisplayInfo> display,
            @JsonProperty("proof_types_supported") Map<String, ProofType> proofTypesSupported
    ) {
    }

    @Builder
    public record DisplayInfo(
            @JsonProperty("name") String name,
            @JsonProperty("locale") String locale,
            @JsonProperty("logo") LogoInfo logo,
            @JsonProperty("background_color") String backgroundColor,
            @JsonProperty("text_color") String textColor
    ) {
    }

    @Builder
    public record LogoInfo(
            @JsonProperty("uri") String uri,
            @JsonProperty("alt_text") String altText
    ) {
    }

    @Builder
    public record ProofType(
            @JsonProperty("proof_signing_alg_values_supported") List<String> proofSigningAlgValues
    ) {
    }

}

