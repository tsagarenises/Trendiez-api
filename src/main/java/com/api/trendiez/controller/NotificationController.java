package com.api.trendiez.controller;

import com.api.trendiez.models.Notification;
import com.api.trendiez.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationRepository;

    @GetMapping("")
    public ResponseEntity<List<Notification>> getNotifications(@RequestHeader("uid") String uid) {
        System.out.println(uid);
        List<Notification> notifications = notificationRepository.findByRecipientAndTypeOrderByCreatedAtDesc(uid, "friend");
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUnreadNotificationsCount(@RequestHeader("uid") String uid) {
        long count = notificationRepository.countByRecipientAndTypeAndIsRead(uid, "friend", false);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable String id) {
        Notification notification = (Notification) notificationRepository.findById(id).orElse(null);
        if (notification == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notification);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable String id) {
        Notification notification = (Notification) notificationRepository.findById(id).orElse(null);
        if (notification == null) {
            return ResponseEntity.notFound().build();
        }
        notification.setRead(true);
        notificationRepository.save(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }
}
