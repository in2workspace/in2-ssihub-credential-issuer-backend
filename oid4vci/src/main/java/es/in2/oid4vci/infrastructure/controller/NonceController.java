package es.in2.oid4vci.infrastructure.controller;

import es.in2.oid4vci.domain.dto.NonceResponse;
import es.in2.oid4vci.domain.services.NonceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oid4vci/nonce")
@RequiredArgsConstructor
public class NonceController {

    private final NonceService nonceService;

    @PostMapping
    public ResponseEntity<NonceResponse> requestNonce() {
        NonceResponse nonceResponse = nonceService.generateNonce();
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore()) // Evita el almacenamiento en caché
                .body(nonceResponse);
    }

}

