package com.sxc.natasha.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by longpo on 2015/3/23.
 */
public class AccountDetail implements Serializable {

    private int id;
    private int accountId;
    private int userId;
    private Long changeFee;
    private int changeType;
    private Date changeTime;
    private String operatorUserId;
    private int state;
    private Long balanceSnapshoot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getChangeFee() {
        return changeFee;
    }

    public void setChangeFee(Long changeFee) {
        this.changeFee = changeFee;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(String operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getBalanceSnapshoot() {
        return balanceSnapshoot;
    }

    public void setBalanceSnapshoot(Long balanceSnapshoot) {
        this.balanceSnapshoot = balanceSnapshoot;
    }
}
