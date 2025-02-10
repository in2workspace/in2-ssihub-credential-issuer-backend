package es.in2.oid4vci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NotificationRequest(
        @JsonProperty("notification_id") String notificationId,
        @JsonProperty("event") String event,
        @JsonProperty("event_description") String eventDescription
) {
}

