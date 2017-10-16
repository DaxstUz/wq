package com.bigpush.resp;

import java.io.Serializable;
import java.util.List;

public class HotKeyResp implements Serializable {


    /**
     * Data : [{"text":"回力鞋"},{"text":"雪地靴"},{"text":"瓜子"},{"text":"裙子"},{"text":"零食"},{"text":"零食2"},{"text":"零食1"},{"text":"零食3"},{"text":"零食4"},{"text":"零食5"}]
     * status : 1
     */

    private int status;
    private List<DataBean> Data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * text : 回力鞋
         */

        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
