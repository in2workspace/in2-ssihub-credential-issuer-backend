package es.in2.oid4vci.domain.services;

import es.in2.oid4vci.domain.dto.CredentialRequest;
import es.in2.oid4vci.domain.dto.CredentialResponse;

public interface CredentialService {
    CredentialResponse processCredentialRequest(CredentialRequest request);
}
