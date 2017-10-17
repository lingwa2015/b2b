package com.b2b.common.domain;

import java.math.BigDecimal;

public class ItemWeekVolume {
    private Integer itemId;

    private BigDecimal weekvolume;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getWeekvolume() {
        return weekvolume;
    }

    public void setWeekvolume(BigDecimal weekvolume) {
        this.weekvolume = weekvolume;
    }
}