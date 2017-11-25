package com.bigpush.resp;

public class GetVersionResp {


    /**
     * Android : {"verNo":"1.0.3","text":"杀了一个后端祭天"}
     * IOS : {"verNo":"1.2.2","text":"杀了个后端祭天"}
     * status : 1
     */

    private AndroidBean Android;
    private IOSBean IOS;
    private int status;

    public AndroidBean getAndroid() {
        return Android;
    }

    public void setAndroid(AndroidBean Android) {
        this.Android = Android;
    }

    public IOSBean getIOS() {
        return IOS;
    }

    public void setIOS(IOSBean IOS) {
        this.IOS = IOS;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class AndroidBean {
        /**
         * verNo : 1.0.3
         * text : 杀了一个后端祭天
         */

        private String verNo;
        private String text;

        public String getVerNo() {
            return verNo;
        }

        public void setVerNo(String verNo) {
            this.verNo = verNo;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class IOSBean {
        /**
         * verNo : 1.2.2
         * text : 杀了个后端祭天
         */

        private String verNo;
        private String text;

        public String getVerNo() {
            return verNo;
        }

        public void setVerNo(String verNo) {
            this.verNo = verNo;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
