package com.bigpush.resp;

import com.bigpush.domain.GoodsDetail;

import java.io.Serializable;

public class GoodsDetailResp implements Serializable {
    private int status;

    private GoodsDetail Data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GoodsDetail getData() {
        return Data;
    }

    public void setData(GoodsDetail data) {
        Data = data;
    }
}
