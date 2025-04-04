package ru.vels.orders.delegate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.vels.orders.dto.CustomerDto;
import ru.vels.orders.dto.Email;
import ru.vels.orders.dto.NotificationDto;
import ru.vels.orders.dto.Telegram;

@Component("notificationDelegate")
@RequiredArgsConstructor
public class NotificationDelegate implements JavaDelegate {

    @Value("${topic.notification}")
    private String sendClientTopic;

    private final KafkaTemplate<String, NotificationDto> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        CustomerDto customer = objectMapper.readValue((String) execution.getVariable("customer"), CustomerDto.class);
        NotificationDto notificationDto= new NotificationDto();
        if (StringUtils.isNotBlank(customer.getEmail())) {
            Email email = new Email();
            email.setTitle("Уведомление о заказе");
            email.setText("Ваш заказ принят, ожидайте курьера!");
            email.setAddress(customer.getEmail());
            notificationDto.setEmail(email);
        }
        if (StringUtils.isNotBlank(customer.getPhone())) {
            Telegram telegram = new Telegram();
            telegram.setText("Ваш заказ принят, ожидайте курьера!");
            telegram.setPhone(customer.getPhone());
            notificationDto.setTelegram(telegram);
        }

        kafkaTemplate.send(sendClientTopic, execution.getBusinessKey(), notificationDto);
    }
}
