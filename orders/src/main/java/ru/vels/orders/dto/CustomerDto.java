package ru.vels.orders.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String id;
    private String fio;
    private String phone;
    private String email;
    private String address;
}
