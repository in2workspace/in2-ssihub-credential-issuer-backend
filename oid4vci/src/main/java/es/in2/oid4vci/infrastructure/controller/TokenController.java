package es.in2.oid4vci.infrastructure.controller;

import es.in2.oid4vci.domain.exception.TokenErrorResponse;
import es.in2.oid4vci.domain.dto.TokenRequest;
import es.in2.oid4vci.domain.dto.TokenResponse;
import es.in2.oid4vci.domain.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oid4vci/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> requestToken(@RequestBody TokenRequest tokenRequest) {
        try {
            TokenResponse response = tokenService.processTokenRequest(tokenRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new TokenErrorResponse(e.getMessage()));
        }
    }
}

