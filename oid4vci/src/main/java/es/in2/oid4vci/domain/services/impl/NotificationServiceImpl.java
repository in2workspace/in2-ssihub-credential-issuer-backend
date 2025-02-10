package es.in2.oid4vci.domain.services.impl;

import es.in2.oid4vci.domain.dto.NotificationRequest;
import es.in2.oid4vci.domain.services.NotificationService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Set<String> validNotificationIds = new HashSet<>();

    public void storeNotificationId(String notificationId) {
        validNotificationIds.add(notificationId);
    }

    @Override
    public void processNotification(NotificationRequest request) {
        if (!validNotificationIds.contains(request.notificationId())) {
            throw new IllegalArgumentException("invalid_notification_id: The notification_id in the Notification Request was invalid.");
        }

        if (!isValidEvent(request.event())) {
            throw new IllegalArgumentException("invalid_notification_request: Unsupported event type.");
        }

        // Idempotencia: simplemente acepta notificaciones repetidas
//        return true;
    }

    private boolean isValidEvent(String event) {
        return "credential_accepted".equals(event) ||
                "credential_failure".equals(event) ||
                "credential_deleted".equals(event);
    }

}
