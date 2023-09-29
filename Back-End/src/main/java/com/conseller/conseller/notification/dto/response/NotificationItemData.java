package com.conseller.conseller.notification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationItemData {
    private Long notificationIdx;
    private Integer notificationType;
    private String notificationCreatedDate;
    private String notificationStatus;
}
