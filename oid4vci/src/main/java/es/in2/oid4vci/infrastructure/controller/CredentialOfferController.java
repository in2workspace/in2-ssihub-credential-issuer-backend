package es.in2.oid4vci.infrastructure.controller;

import es.in2.oid4vci.domain.dto.CredentialOffer;
import es.in2.oid4vci.domain.services.CredentialOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oid4vci/credential-offer")
@RequiredArgsConstructor
public class CredentialOfferController {

    private final CredentialOfferService credentialOfferService;

    @GetMapping("/{nonce}")
    public ResponseEntity<CredentialOffer> getCredentialOffer(@PathVariable String nonce) {
        return ResponseEntity.ok(credentialOfferService.generateCredentialOffer(nonce));
    }

}
