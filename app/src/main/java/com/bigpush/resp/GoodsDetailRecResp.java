package com.bigpush.resp;

import com.bigpush.domain.GoodRecommend;
import com.bigpush.domain.GoodsDetail;

import java.io.Serializable;
import java.util.List;

public class GoodsDetailRecResp implements Serializable {
    private int status;

    private List<GoodRecommend> Data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<GoodRecommend> getData() {
        return Data;
    }

    public void setData(List<GoodRecommend> data) {
        Data = data;
    }
}
