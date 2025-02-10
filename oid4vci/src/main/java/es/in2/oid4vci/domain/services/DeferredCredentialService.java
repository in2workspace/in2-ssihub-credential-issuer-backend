package es.in2.oid4vci.domain.services;

import es.in2.oid4vci.domain.dto.DeferredCredentialRequest;
import es.in2.oid4vci.domain.dto.DeferredCredentialResponse;

public interface DeferredCredentialService {
    DeferredCredentialResponse processDeferredCredentialRequest(DeferredCredentialRequest request);
}
