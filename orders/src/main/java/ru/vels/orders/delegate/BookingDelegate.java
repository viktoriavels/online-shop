package ru.vels.orders.delegate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.vels.orders.client.ProductClient;
import ru.vels.orders.dto.GoodsDto;

@Component("bookingDelegate")
@RequiredArgsConstructor
public class BookingDelegate implements JavaDelegate {

    private final ProductClient productClient;
    private final ObjectMapper objectMapper;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        GoodsDto good = objectMapper.readValue((String) execution.getVariable("goods"), GoodsDto.class);
        productClient.booking(execution.getBusinessKey(), good);
    }
}
