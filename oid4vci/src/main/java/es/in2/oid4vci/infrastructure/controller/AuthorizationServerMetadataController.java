package es.in2.oid4vci.infrastructure.controller;

import es.in2.oid4vci.domain.dto.AuthorizationServerMetadata;
import es.in2.oid4vci.domain.services.AuthorizationServerMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/.well-known/oauth-authorization-server")
@RequiredArgsConstructor
public class AuthorizationServerMetadataController {

    private final AuthorizationServerMetadataService metadataService;

    @GetMapping
    public ResponseEntity<AuthorizationServerMetadata> getMetadata() {
        return ResponseEntity.ok(metadataService.getMetadata());
    }
}

