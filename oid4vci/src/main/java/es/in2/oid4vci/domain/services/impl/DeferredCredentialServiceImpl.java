package es.in2.oid4vci.domain.services.impl;

import es.in2.oid4vci.domain.dto.CredentialResponse;
import es.in2.oid4vci.domain.dto.DeferredCredentialRequest;
import es.in2.oid4vci.domain.dto.DeferredCredentialResponse;
import es.in2.oid4vci.domain.services.DeferredCredentialService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DeferredCredentialServiceImpl implements DeferredCredentialService {

    private final Map<String, List<String>> pendingTransactions = new HashMap<>();

    public void storePendingCredential(String transactionId, List<String> credentials) {
        pendingTransactions.put(transactionId, credentials);
    }

    @Override
    public DeferredCredentialResponse processDeferredCredentialRequest(DeferredCredentialRequest request) {
        if (!pendingTransactions.containsKey(request.transactionId())) {
            throw new IllegalArgumentException("invalid_transaction_id: Transaction ID not found or already used.");
        }

        List<String> credentials = pendingTransactions.get(request.transactionId());
        if (credentials == null || credentials.isEmpty()) {
            throw new IllegalArgumentException("issuance_pending: Credential issuance still pending.");
        }

        // Simulación de notificación única
        String notificationId = UUID.randomUUID().toString();
        List<CredentialResponse.Credential> credentialObjects = credentials.stream()
                .map(CredentialResponse.Credential::new)
                .toList();

        // Eliminamos la transacción una vez completada
        pendingTransactions.remove(request.transactionId());

        return new DeferredCredentialResponse(credentialObjects, notificationId);
    }

}
