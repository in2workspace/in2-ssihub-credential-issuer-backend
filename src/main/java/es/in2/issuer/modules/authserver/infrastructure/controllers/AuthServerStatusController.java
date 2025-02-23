package es.in2.issuer.modules.authserver.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-server/v1/status")
public class AuthServerStatusController {

    @GetMapping
    public String getStatus() {
        return "Auth Server is running";
    }

}
