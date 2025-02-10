package es.in2.oid4vci.domain.services.impl;

import es.in2.oid4vci.domain.dto.CredentialIssuerMetadata;
import es.in2.oid4vci.domain.services.CredentialIssuerMetadataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CredentialIssuerMetadataServiceImpl implements CredentialIssuerMetadataService {

    @Value("${oid4vci.credential-issuer}")
    private String credentialIssuer;

    public CredentialIssuerMetadata getMetadata() {
        return CredentialIssuerMetadata.builder()
                .credentialIssuer(credentialIssuer)
                .authorizationServers(List.of(credentialIssuer)) // Authorization servers opcionales. Dejamos el mismo que el emisor de credenciales
                .credentialEndpoint(credentialIssuer + "/oid4vci/credential")
                .nonceEndpoint(credentialIssuer + "/oid4vci/nonce")
                .deferredCredentialEndpoint(credentialIssuer + "/oid4vci/deferred_credential")
                .notificationEndpoint(credentialIssuer + "/oid4vci/notification")
                .encryptionSupport(CredentialIssuerMetadata.EncryptionSupport.builder()
                        .algValuesSupported(List.of("ES256"))
                        .encValuesSupported(List.of("A128CBC-HS256")) // todo: revisar
                        .encryptionRequired(true)
                        .build()
                )
                .credentialConfigurations(Map.of(
                        "LEARCredentialEmployee",
                        CredentialIssuerMetadata.CredentialConfiguration.builder()
                                .format("jwt_vc_json")
                                .cryptographicBindingMethods(List.of("jwk"))
                                .credentialSigningAlgValues(List.of("ES256"))
                                .display(List.of(
                                        CredentialIssuerMetadata.DisplayInfo.builder()
                                                .name("Employee Credential")
                                                .locale("en-US")
                                                .backgroundColor("#12107c")
                                                .textColor("#FFFFFF")
                                                .build()
                                ))
                                .proofTypesSupported(Map.of("jwt", CredentialIssuerMetadata.ProofType.builder()
                                        .proofSigningAlgValues(List.of("ES256"))
                                        .build()
                                ))
                                .build()
                ))
                .build();
    }

}

