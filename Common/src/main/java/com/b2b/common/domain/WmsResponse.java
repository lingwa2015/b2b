package com.b2b.common.domain;

import java.util.List;

/**
 * Created by a. on 2017/10/11.
 */
public class WmsResponse {

    private String flag;

    private String code;

    private String message;

    private List<WarehouseSyncList> warehouseSyncList;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public List<WarehouseSyncList> getWarehouseSyncList() {
//        return warehouseSyncList;
//    }
//
//    public void setWarehouseSyncList(List<WarehouseSyncList> warehouseSyncList) {
//        this.warehouseSyncList = warehouseSyncList;
//    }
}
