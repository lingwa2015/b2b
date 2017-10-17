package com.b2b.common.domain;

import java.math.BigDecimal;

public class ItemDailyReportKey {
    private Integer id;

    private BigDecimal turnoverRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(BigDecimal turnoverRate) {
        this.turnoverRate = turnoverRate;
    }
}