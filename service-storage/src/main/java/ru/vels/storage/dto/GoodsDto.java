package ru.vels.storage.dto;

import java.util.List;

public class GoodsDto {
    public List<GoodDto> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodDto> goods) {
        this.goods = goods;
    }

    private List<GoodDto> goods;
}
