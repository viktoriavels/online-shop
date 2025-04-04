package ru.vels.storage.dto;

public class GoodDto {
    public String getId() {
        return id;
    }

    public void setId(String productId) {
        this.id = productId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    private String id;
    private Long count;
}
