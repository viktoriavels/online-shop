package ru.vels.orders.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vels.orders.dto.OrderDto;
import ru.vels.orders.service.OrderService;

@RestController
@Tag(name = "Order", description = "The orders API")
public class OrdersController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    @PreAuthorize("isAuthenticated()")
    public void createOrder(@RequestHeader("Authorization") String authorization, @RequestBody OrderDto order) {
        orderService.createOrder(order);
    }


}
