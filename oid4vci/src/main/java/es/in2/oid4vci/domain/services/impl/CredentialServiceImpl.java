package es.in2.oid4vci.domain.services.impl;

import es.in2.oid4vci.domain.dto.CredentialRequest;
import es.in2.oid4vci.domain.dto.CredentialResponse;
import es.in2.oid4vci.domain.dto.CredentialImmediateResponse;
import es.in2.oid4vci.domain.services.CredentialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Override
    public CredentialResponse processCredentialRequest(CredentialRequest request) {
        // Validación de exclusividad entre credential_identifier y credential_configuration_id
        validateExclusivity(request.credentialIdentifier(), request.credentialConfigurationId());

        // Validación de prueba de posesión (proof o proofs)
        validateProofs(request);

        // Simulación de validación de nonce (debería compararse con valores almacenados en BD)
        validateNonce(request.proof() != null ? request.proof().jwt() : null);

        // Simulación de emisión de credencial en formato JWT
        String credentialJwt = UUID.randomUUID().toString();

        // No se usa transaction_id porque se emite de inmediato
        return CredentialImmediateResponse.builder()
                .credentials(List.of(CredentialImmediateResponse.Credential.builder()
                        .credential(credentialJwt)
                        .build()))
                .build();
    }

    private void validateExclusivity(String credentialIdentifier, String credentialConfigurationId) {
        if ((credentialIdentifier != null && credentialConfigurationId != null) ||
                (credentialIdentifier == null && credentialConfigurationId == null)) {
            throw new IllegalArgumentException("invalid_credential_request: Must include exactly one of credential_identifier or credential_configuration_id.");
        }
    }

    private void validateProofs(CredentialRequest request) {
        if (request.proof() == null && request.proofs() == null) {
            throw new IllegalArgumentException("invalid_proof: Missing required proof parameter.");
        }
        if (request.proof() != null && request.proofs() != null) {
            throw new IllegalArgumentException("invalid_proof: Cannot include both proof and proofs parameters.");
        }
    }

    private void validateNonce(String nonce) {
        // Simulación de validación de nonce (debería compararse con valores almacenados en BD)
        if ("invalid_nonce_value".equals(nonce)) {
            throw new IllegalArgumentException("invalid_nonce: Nonce value is invalid or expired.");
        }
    }

}

