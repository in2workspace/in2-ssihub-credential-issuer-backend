package es.in2.issuer.schema.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schema/v1/status")
public class SchemaStatusController {

    @GetMapping
    public String getStatus() {
        return "Schema is running";
    }

}
