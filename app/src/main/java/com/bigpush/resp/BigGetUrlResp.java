package com.bigpush.resp;

public class BigGetUrlResp {


    /**
     * Data : {"url":"https://uland.taobao.com/coupon/edetail?e=fod6EsnY%2BZA8Clx5mXPEKtf%2F34uld0uYeJoS%2FC1%2BC8W4R%2BK27mdIllCIvsg9euI%2Bzs7UK7s7RlIfRY4My3zYs5Bh%2BsFgnewCTIaxC3l6P%2BzYhpVVy38fp9pR0UZ0ttC1&traceId=0bba60d815101558063061746"}
     * status : 1
     */

    private DataBean Data;
    private int status;

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * url : https://uland.taobao.com/coupon/edetail?e=fod6EsnY%2BZA8Clx5mXPEKtf%2F34uld0uYeJoS%2FC1%2BC8W4R%2BK27mdIllCIvsg9euI%2Bzs7UK7s7RlIfRY4My3zYs5Bh%2BsFgnewCTIaxC3l6P%2BzYhpVVy38fp9pR0UZ0ttC1&traceId=0bba60d815101558063061746
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
