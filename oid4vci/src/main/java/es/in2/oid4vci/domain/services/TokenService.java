package es.in2.oid4vci.domain.services;

import es.in2.oid4vci.domain.dto.TokenRequest;
import es.in2.oid4vci.domain.dto.TokenResponse;

public interface TokenService {
    TokenResponse processTokenRequest(TokenRequest tokenRequest);
}
