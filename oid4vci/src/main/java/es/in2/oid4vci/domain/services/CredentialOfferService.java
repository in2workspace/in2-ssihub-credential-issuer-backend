package es.in2.oid4vci.domain.services;

import es.in2.oid4vci.domain.dto.CredentialOffer;

public interface CredentialOfferService {
    CredentialOffer generateCredentialOffer(String nonce);
}
