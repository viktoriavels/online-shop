package ru.vels.storage.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vels.storage.dto.GoodsDto;
import ru.vels.storage.dto.ProductDto;
import ru.vels.storage.entity.Product;
import ru.vels.storage.service.ProductsService;

import java.util.List;

@RestController
@Tag(name = "Products", description = "The products API")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @GetMapping("/products/getProducts")
    @PreAuthorize("isAuthenticated()")
    public List<Product> getProducts(@RequestHeader("Authorization") String authorization,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false, defaultValue = "false") Boolean availableOnly,
                                     @RequestParam(required = false, defaultValue = "false") Boolean enabledOnly,
                                     @RequestParam(required = false, defaultValue = "0") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer size,
                                     @RequestParam(required = false) Integer minWeight,
                                     @RequestParam(required = false) Integer maxWeight,
                                     @RequestParam(required = false) String article) {
        return productsService.getProducts(name, availableOnly, enabledOnly, page, size, minWeight, maxWeight, article);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@RequestHeader("Authorization") String authorization,
                                  @PathVariable(required = true) String id) {
        return productsService.getProduct(id);
    }

    @PostMapping("/products/addProduct")
    public void addProduct(@RequestBody(required = true) ProductDto product) {
        productsService.addProduct(product);
    }

    @PostMapping("/products/{id}/enabled")
    public String determineEnableTrue(@RequestHeader("Authorization") String authorization,
                                      @PathVariable(required = true) String id) {
        return productsService.determineEnableTrue(id);
    }

    @PostMapping("/products/{id}/disable")
    public String determineEnableFalse(@RequestHeader("Authorization") String authorization,
                                       @PathVariable(required = true) String id) {
        return productsService.determineEnableFalse(id);
    }

    @PostMapping("/products/{id}/refill-good/{count}")
    public String refillGood(@RequestHeader("Authorization") String authorization,
                             @PathVariable(required = true) String id, @PathVariable(required = true) Long count) {
        return productsService.refillGood(id, count);
    }

    @PostMapping("/products/{id}/write-off/{count}")
    public String writeOff(@RequestHeader("Authorization") String authorization,
                           @PathVariable(required = true) String id, @PathVariable(required = true) Long count) {
        return productsService.writeOff(id, count);
    }


    @PostMapping("/products/booking/{id}")
    public void booking(@RequestHeader("Authorization") String authorization,
                        @PathVariable(required = true) String id,
                        @RequestBody(required = true) GoodsDto goodDto) {
        productsService.booking(goodDto.getGoods());
    }

    @PostMapping("/products/bookingDelete/{bookingId}")
    public void deleteBooking(@RequestHeader("Authorization") String authorization,
                        @PathVariable(required = true) String bookingId) {
        productsService.deleteBooking(bookingId);
    }
}
