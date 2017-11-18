package com.bigpush.resp;

public class ReceiveResp {

    /**
     * Data : {"url":"https://uland.taobao.com/coupon/edetail?e=h3DnZ5G53NAGQASttHIRqVK2SHG0XI6i7okXQMudSGWbLYemSs1A4HFxgG5Bq3N%2B6sen2Z%2Bh%2BO6EAjd3IwsZR5Q5wfGz%2Fu%2BNcP8OSPWYQhPTulAYBVb7JmuFqp8TFaHMonv6QcvcARY%3D&traceId=0ab2518615088619271273165"}
     * status : 1
     */

    private DataBean Data;
    private int status;
    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

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
         * url : https://uland.taobao.com/coupon/edetail?e=h3DnZ5G53NAGQASttHIRqVK2SHG0XI6i7okXQMudSGWbLYemSs1A4HFxgG5Bq3N%2B6sen2Z%2Bh%2BO6EAjd3IwsZR5Q5wfGz%2Fu%2BNcP8OSPWYQhPTulAYBVb7JmuFqp8TFaHMonv6QcvcARY%3D&traceId=0ab2518615088619271273165
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
