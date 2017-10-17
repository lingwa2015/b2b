package com.b2b.common.domain;

import java.util.Date;

public class WarehouseSync {
    private Long id;

    private Date syncDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }
}