package es.in2.issuer.modules.oid4vci.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oid4vci/v1/status")
public class OID4VCIStatusController {

    @GetMapping
    public String getStatus() {
        return "OID4VCI is running";
    }

}
