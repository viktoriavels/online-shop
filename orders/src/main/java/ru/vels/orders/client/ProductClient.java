package ru.vels.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.vels.orders.dto.GoodsDto;

@FeignClient(name = "products", url = "${spring.cloud.openfeign.client.config.products.url}", configuration = FeignClientConfiguration.class)

public interface ProductClient {

    @PostMapping("/products/booking/{id}")
    void booking(@PathVariable(required = true) String id,
                 @RequestBody(required = true) GoodsDto goodDto);

    @PostMapping("/products/bookingDelete/{bookingId}")
    void deleteBooking(@PathVariable(required = true) String bookingId);
}
