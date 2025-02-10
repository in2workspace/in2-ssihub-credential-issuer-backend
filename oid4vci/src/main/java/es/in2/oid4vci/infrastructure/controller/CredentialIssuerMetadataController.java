package es.in2.oid4vci.infrastructure.controller;

import es.in2.oid4vci.domain.dto.CredentialIssuerMetadata;
import es.in2.oid4vci.domain.services.CredentialIssuerMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/.well-known/openid-credential-issuer")
@RequiredArgsConstructor
public class CredentialIssuerMetadataController {

    private final CredentialIssuerMetadataService metadataService;

    @GetMapping
    public ResponseEntity<CredentialIssuerMetadata> getMetadata() {
        return ResponseEntity.ok(metadataService.getMetadata());
    }

}

