package ru.vels.notification;

import lombok.Data;

@Data
public class NotificationMessage {
    private Email email;
    private Telegram telegram;
}
