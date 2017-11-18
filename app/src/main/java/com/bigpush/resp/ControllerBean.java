package com.bigpush.resp;

public class ControllerBean {


    /**
     * rcCode : success
     * msg : 成功
     * data : {"id":4,"tel":"15273836479","chargeTime":1498535514000,"startTime":1498535516000,"endTime":1507644688000,"mounths":26}
     */

    private String rcCode;
    private String msg;
    private DataBean data;

    public String getRcCode() {
        return rcCode;
    }

    public void setRcCode(String rcCode) {
        this.rcCode = rcCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * tel : 15273836479
         * chargeTime : 1498535514000
         * startTime : 1498535516000
         * endTime : 1507644688000
         * mounths : 26
         */

        private int id;
        private String tel;
        private long chargeTime;
        private long startTime;
        private long endTime;
        private int mounths;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public long getChargeTime() {
            return chargeTime;
        }

        public void setChargeTime(long chargeTime) {
            this.chargeTime = chargeTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getMounths() {
            return mounths;
        }

        public void setMounths(int mounths) {
            this.mounths = mounths;
        }
    }
}
