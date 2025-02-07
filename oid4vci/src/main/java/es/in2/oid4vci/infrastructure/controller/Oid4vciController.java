package es.in2.oid4vci.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/oid4vci")
public class Oid4vciController {

    @GetMapping("/v1/status")
    public ResponseEntity<String> getStatus() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return ResponseEntity.ok("OID4VCI Service is running");
    }

}
