package es.in2.oid4vci.infrastructure.controller;

import es.in2.oid4vci.domain.dto.CredentialRequest;
import es.in2.oid4vci.domain.dto.CredentialResponse;
import es.in2.oid4vci.domain.exception.CredentialErrorResponse;
import es.in2.oid4vci.domain.services.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oid4vci/credential")
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialService credentialService;

    @PostMapping
    public ResponseEntity<?> requestCredential(@RequestBody CredentialRequest credentialRequest,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(CredentialErrorResponse.builder()
                                .error("invalid_token")
                                .errorDescription("Missing or invalid access token.")
                                .build());
            }

            // CredentialResponse es una interfaz que tiene 2 implementaciones:
            // ImmediateCredentialResponse y DeferredCredentialResponse
            CredentialResponse response = credentialService.processCredentialRequest(credentialRequest);
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noStore()) // Evita cacheo de la respuesta
                    .body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(CredentialErrorResponse.builder()
                    .error("invalid_request")
                    .errorDescription(e.getMessage() + " Invalid request parameters.")
                    .build());
        }
    }

}

