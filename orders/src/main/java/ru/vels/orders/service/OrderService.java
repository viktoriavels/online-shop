package ru.vels.orders.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vels.orders.dto.CustomerDto;
import ru.vels.orders.dto.GoodsDto;
import ru.vels.orders.dto.OrderDto;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RuntimeService runtimeService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void createOrder(OrderDto order) {
        String goods = objectMapper.writeValueAsString(new GoodsDto(order.getGoods()));
        String customer = objectMapper.writeValueAsString(order.getCustomer());
        runtimeService.startProcessInstanceByKey(
                "order-proc",
                UUID.randomUUID().toString(),
                Map.of("goods", goods, "customer", customer));
    }


}
