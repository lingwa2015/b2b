
package com.b2b.dto;

/**
 * 统计用户每个月订单数据
 *
 * @author hades
 */
public class OrderMonthDto {
    
    private String month;
    private Long totalFee;
    
    public OrderMonthDto(String month, Long totalFee) {
        this.month = month;
        this.totalFee = totalFee;
    }

    
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }
}
