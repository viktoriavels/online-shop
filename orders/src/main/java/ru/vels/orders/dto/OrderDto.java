package ru.vels.orders.dto;

import lombok.*;

import java.util.List;

@Data
public class OrderDto {
    private List<GoodDto> goods;
    private CustomerDto customer;
}
