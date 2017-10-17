package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class DailyReport {
    private Integer id;

    private Date sumDate;

    private Integer openShopNum;

    private Integer consumeShopNum;

    private Integer beforeLastWeekNum;

    private Long totalConsume;

    private BigDecimal beforeLastWeekConsume;

    private Long avgConsume;

    private BigDecimal beforeLastWeekAvg;

    private Long totalExpend;

    private Integer consumeNum;

    private BigDecimal beforeLastWeekConsumeNum;

    private Integer consumePen;

    private BigDecimal beforeLastWeekConsumePen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSumDate() {
        return sumDate;
    }

    public void setSumDate(Date sumDate) {
        this.sumDate = sumDate;
    }

    public Integer getOpenShopNum() {
        return openShopNum;
    }

    public void setOpenShopNum(Integer openShopNum) {
        this.openShopNum = openShopNum;
    }

    public Integer getConsumeShopNum() {
        return consumeShopNum;
    }

    public void setConsumeShopNum(Integer consumeShopNum) {
        this.consumeShopNum = consumeShopNum;
    }

    public Integer getBeforeLastWeekNum() {
        return beforeLastWeekNum;
    }

    public void setBeforeLastWeekNum(Integer beforeLastWeekNum) {
        this.beforeLastWeekNum = beforeLastWeekNum;
    }

    public Long getTotalConsume() {
        return totalConsume;
    }

    public void setTotalConsume(Long totalConsume) {
        this.totalConsume = totalConsume;
    }

    public BigDecimal getBeforeLastWeekConsume() {
        return beforeLastWeekConsume;
    }

    public void setBeforeLastWeekConsume(BigDecimal beforeLastWeekConsume) {
        this.beforeLastWeekConsume = beforeLastWeekConsume;
    }

    public Long getAvgConsume() {
        return avgConsume;
    }

    public void setAvgConsume(Long avgConsume) {
        this.avgConsume = avgConsume;
    }

    public BigDecimal getBeforeLastWeekAvg() {
        return beforeLastWeekAvg;
    }

    public void setBeforeLastWeekAvg(BigDecimal beforeLastWeekAvg) {
        this.beforeLastWeekAvg = beforeLastWeekAvg;
    }

    public Long getTotalExpend() {
        return totalExpend;
    }

    public void setTotalExpend(Long totalExpend) {
        this.totalExpend = totalExpend;
    }

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }

    public BigDecimal getBeforeLastWeekConsumeNum() {
        return beforeLastWeekConsumeNum;
    }

    public void setBeforeLastWeekConsumeNum(BigDecimal beforeLastWeekConsumeNum) {
        this.beforeLastWeekConsumeNum = beforeLastWeekConsumeNum;
    }

    public Integer getConsumePen() {
        return consumePen;
    }

    public void setConsumePen(Integer consumePen) {
        this.consumePen = consumePen;
    }

    public BigDecimal getBeforeLastWeekConsumePen() {
        return beforeLastWeekConsumePen;
    }

    public void setBeforeLastWeekConsumePen(BigDecimal beforeLastWeekConsumePen) {
        this.beforeLastWeekConsumePen = beforeLastWeekConsumePen;
    }
}