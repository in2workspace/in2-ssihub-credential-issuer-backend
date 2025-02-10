package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record CredentialImmediateResponse(
        @JsonProperty("credentials") List<Credential> credentials
)  implements CredentialResponse {

    @Builder
    public record Credential(
            @JsonProperty("credential") String credential
    ) {
    }

}
