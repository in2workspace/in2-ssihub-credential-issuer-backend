package es.in2.oid4vci.domain.services;

import es.in2.oid4vci.domain.dto.NonceResponse;

public interface NonceService {
    NonceResponse  generateNonce();
}
