package com.conseller.conseller.notification;

import com.conseller.conseller.notification.dto.response.NotificationListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{user_idx}")
    public ResponseEntity<Object> getNotificationList(@PathVariable("user_idx") Long userIdx) {

        NotificationListResponse response =  notificationService.getNotificationList(userIdx);

        return ResponseEntity.ok()
                .body(response);
    }


}
