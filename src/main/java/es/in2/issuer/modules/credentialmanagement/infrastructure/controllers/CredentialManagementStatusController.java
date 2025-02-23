package es.in2.issuer.modules.credentialmanagement.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credential-management/v1/status")
public class CredentialManagementStatusController {

    @GetMapping
    public String getStatus() {
        return "Credential Management is running";
    }

}
