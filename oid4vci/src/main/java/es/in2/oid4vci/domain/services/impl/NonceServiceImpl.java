package es.in2.oid4vci.domain.services.impl;

import es.in2.oid4vci.domain.dto.NonceResponse;
import es.in2.oid4vci.domain.services.NonceService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NonceServiceImpl implements NonceService {

    public NonceResponse generateNonce() {
        // Generamos un c_nonce impredecible usando UUID
        String nonce = UUID.randomUUID().toString().replace("-", "");
        return new NonceResponse(nonce);
    }
}

