package es.in2.oid4vci.domain.services;

import es.in2.oid4vci.domain.dto.NotificationRequest;

public interface NotificationService {
    void processNotification(NotificationRequest request);
}
