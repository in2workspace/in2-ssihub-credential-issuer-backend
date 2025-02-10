package es.in2.oid4vci.infrastructure.controller;

import es.in2.oid4vci.domain.dto.NotificationRequest;
import es.in2.oid4vci.domain.dto.NotificationErrorResponse;
import es.in2.oid4vci.domain.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oid4vci/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest notificationRequest,
                                              @RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new NotificationErrorResponse("invalid_token", "Missing or invalid access token."));
            }

            notificationService.processNotification(notificationRequest);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            String error = e.getMessage().split(":")[0];
            String description = e.getMessage().split(":")[1].trim();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new NotificationErrorResponse(error, description));
        }
    }
}

