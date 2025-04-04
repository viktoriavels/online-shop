package ru.vels.storage.model;

import lombok.Builder;
import lombok.Data;
import ru.vels.storage.entity.Bookings;

import java.util.List;
@Builder
@Data
public class ProductsModel {

    private String id;
    private List<Bookings> bookings;
    private String article;
    private String name;
    private Long weight;
    private String weightUnits;
    private Long count;
    private String dimensions;
    private String dimensionsUnits;
    private Boolean enabled;
}
