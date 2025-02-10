package es.in2.oid4vci.domain.services.impl;

import es.in2.oid4vci.domain.dto.CredentialOffer;
import es.in2.oid4vci.domain.services.CredentialOfferService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CredentialOfferServiceImpl implements CredentialOfferService {

    @Value("${oid4vci.credential-issuer}")
    private String credentialIssuer;

    @Override
    public CredentialOffer generateCredentialOffer(String nonce) {
        return CredentialOffer.builder()
                .credentialIssuer(credentialIssuer)
                // todo: configurar los identificadores de configuración de credenciales de forma dinámica
                .credentialConfigurationIds(Collections.singletonList("LEARCredentialEmployee"))
                .grants(Map.of(
                        "urn:ietf:params:oauth:grant-type:pre-authorized_code",
                        CredentialOffer.Grant.builder()
                                .preAuthorizedCode(generatePreAuthorizedCode(nonce))
                                .txCode(CredentialOffer.Grant.TransactionCode.builder()
                                        .inputMode("numeric")
                                        .length(6)
                                        .description("Enter the code sent via email")
                                        .build())
                                .build()
                ))
                .build();
    }

    private String generatePreAuthorizedCode(String nonce) {
        // todo: este codigo debería ser generado y almacenado en BD o generado por el Authorization Server
        return nonce + "-preAuthCode";
    }

}
