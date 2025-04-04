package ru.vels.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    private final String TELEGRAM_API_URL = "https://api.telegram.org/bot<7212039561:AAGb0hq23Ob9rO4BXlHiSU2s08BERtdgAL0>/sendMessage";

    @Bean
    public Consumer<Message<NotificationMessage>> consumeNotificationCommands() {
        return message -> {
            Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);

            NotificationMessage notificationMessage = message.getPayload();
            if (notificationMessage.getTelegram() != null) {
                sendTelegramMessage(notificationMessage.getTelegram());
            }
            if (notificationMessage.getEmail() != null) {
                sendEmail(notificationMessage.getEmail());
            }

            if (acknowledgment != null) {
                acknowledgment.acknowledge();
            }
        };
    }

    private void sendEmail(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getAddress());
        mailMessage.setSubject(email.getTitle());
        mailMessage.setText(email.getText());
        mailSender.send(mailMessage);
    }

    private void sendTelegramMessage(Telegram telegram) {
        RestTemplate restTemplate = new RestTemplate();
        String url = TELEGRAM_API_URL + "?chat_id=" + telegram.getPhone() + "&text=" + telegram.getText();
        restTemplate.getForObject(url, String.class);
    }
}
