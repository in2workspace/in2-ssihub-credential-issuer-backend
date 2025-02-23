package es.in2.issuer.modules.signature.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signature/v1/status")
public class SignatureStatusController {

    @GetMapping
    public String getStatus() {
        return "Signature is running";
    }

}
