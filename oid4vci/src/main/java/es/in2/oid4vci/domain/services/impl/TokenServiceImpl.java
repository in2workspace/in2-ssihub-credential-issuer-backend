package es.in2.oid4vci.domain.services.impl;

import es.in2.oid4vci.domain.dto.TokenRequest;
import es.in2.oid4vci.domain.dto.TokenResponse;
import es.in2.oid4vci.domain.services.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @GetMapping
    public TokenResponse processTokenRequest(TokenRequest tokenRequest) {
        validateGrantType(tokenRequest.grantType());
        validatePreAuthorizedCode(tokenRequest.preAuthorizedCode());
        // Simulación de un Access Token (en una implementación real, usaríamos JWT)
        // todo: generar un JWT con los claims necesarios de acuerdo a la especificación OIDC
        String accessToken = UUID.randomUUID().toString();
        // Simulación de respuesta con Authorization Details
        return TokenResponse.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(86400)
                .authorizationDetails(List.of(TokenResponse.AuthorizationDetail.builder()
                        .type("openid_credential")
                        .credentialConfigurationId("LEARCredentialEmployee")
                        // todo: ¿Cuáles son los identificadores de credenciales que se deben devolver?
                        .credentialIdentifiers(List.of("CivilEngineeringDegree-2023", "ElectricalEngineeringDegree-2023"))
                        .build()))
                .build();
    }

    private void validateGrantType(String grantType) {
        if (!"urn:ietf:params:oauth:grant-type:pre-authorized_code".equals(grantType)) {
            throw new IllegalArgumentException("invalid_grant");
        }
    }

    private void validatePreAuthorizedCode(String preAuthorizedCode) {
        if (preAuthorizedCode == null || preAuthorizedCode.isEmpty()) {
            throw new IllegalArgumentException("invalid_request");
        }
    }

}

