package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DeferredCredentialResponse(
        @JsonProperty("credentials") List<CredentialImmediateResponse.Credential> credentials,
        @JsonProperty("notification_id") String notificationId
) {
}

