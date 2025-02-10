package es.in2.oid4vci.infrastructure.controller;

import es.in2.oid4vci.domain.dto.DeferredCredentialRequest;
import es.in2.oid4vci.domain.dto.DeferredCredentialResponse;
import es.in2.oid4vci.domain.exception.DeferredCredentialErrorResponse;
import es.in2.oid4vci.domain.services.DeferredCredentialService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oid4vci/deferred_credential")
public class DeferredCredentialController {

    private final DeferredCredentialService deferredCredentialService;

    public DeferredCredentialController(DeferredCredentialService deferredCredentialService) {
        this.deferredCredentialService = deferredCredentialService;
    }

    @PostMapping
    public ResponseEntity<?> requestDeferredCredential(@RequestBody DeferredCredentialRequest request,
                                                       @RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new DeferredCredentialErrorResponse("invalid_token", "Missing or invalid access token.", null));
            }

            DeferredCredentialResponse response = deferredCredentialService.processDeferredCredentialRequest(request);
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noStore()) // Evita cacheo de la respuesta
                    .body(response);
        } catch (IllegalArgumentException e) {
            String error = e.getMessage().split(":")[0];
            String description = e.getMessage().split(":")[1].trim();
            int interval = "issuance_pending".equals(error) ? 5 : 0;

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .cacheControl(CacheControl.noStore())
                    .body(new DeferredCredentialErrorResponse(error, description, interval));
        }
    }
}

