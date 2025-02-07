package es.in2.credentialmanagement.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credential-management")
public class CredentialManagerController {

    @GetMapping("/v1/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Credential Manager Service is running");
    }

}
