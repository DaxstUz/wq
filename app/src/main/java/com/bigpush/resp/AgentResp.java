package com.bigpush.resp;

import java.util.List;

public class AgentResp {


    /**
     * Data : [{"userCode":"U00000001","nickName":"申请人1","createDate":"2017-10-01"},{"userCode":"U00000001","nickName":"申请人1","createDate":"2017-10-01"},{"userCode":"U00000001","nickName":"申请人1","createDate":"2017-10-01"}]
     * status : 1
     */

    private int status;
    private List<DataBean> Data;
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

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
         * userCode : U00000001
         * nickName : 申请人1
         * createDate : 2017-10-01
         */

        private String userCode;
        private String nickName;
        private String createDate;

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
