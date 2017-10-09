package com.bigpush.resp;

import com.bigpush.domain.HomeType;

import java.io.Serializable;
import java.util.List;

public class HomeTypeResp implements Serializable {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private List<HomeType> Data;

    public List<HomeType> getData() {
        return Data;
    }

    public void setData(List<HomeType> data) {
        Data = data;
    }
}
