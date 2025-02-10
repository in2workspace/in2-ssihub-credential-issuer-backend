package es.in2.oid4vci.domain.services.impl;

import es.in2.oid4vci.domain.dto.AuthorizationServerMetadata;
import es.in2.oid4vci.domain.services.AuthorizationServerMetadataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationServerMetadataServiceImpl implements AuthorizationServerMetadataService {

    @Value("${oid4vci.authorization-server}")
    private String authorizationServer;

    @Override
    public AuthorizationServerMetadata getMetadata() {
        // fixme: debería usar builder
        return new AuthorizationServerMetadata(
                authorizationServer,
                authorizationServer + "/oauth2/authorize",
                authorizationServer + "/oauth2/token",
                authorizationServer + "/.well-known/jwks.json",
                List.of("urn:ietf:params:oauth:grant-type:pre-authorized_code"),
                null,
                true // Indica que se admite solicitud de token sin client_id en el flujo Pre-Authorized
        );
    }

}

