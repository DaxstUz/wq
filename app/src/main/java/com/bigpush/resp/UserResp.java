package com.bigpush.resp;

import java.io.Serializable;
import java.util.List;

public class UserResp implements Serializable {


    private DataBean Data;
    private int status;
    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

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

    public static class DataBean  implements Serializable{

        private String phoneNum;
        private String nickName;
        private String userCode;
        private TaobaoKeInfoBean taobaoKeInfo;
        private String numCode;
        private String openSid;
        private String photoUrl;
        private String isCommission;
        private int userType;
        private List<?> BindInfo;

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public TaobaoKeInfoBean getTaobaoKeInfo() {
            return taobaoKeInfo;
        }

        public void setTaobaoKeInfo(TaobaoKeInfoBean taobaoKeInfo) {
            this.taobaoKeInfo = taobaoKeInfo;
        }

        public String getNumCode() {
            return numCode;
        }

        public void setNumCode(String numCode) {
            this.numCode = numCode;
        }

        public String getOpenSid() {
            return openSid;
        }

        public void setOpenSid(String openSid) {
            this.openSid = openSid;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getIsCommission() {
            return isCommission;
        }

        public void setIsCommission(String isCommission) {
            this.isCommission = isCommission;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public List<?> getBindInfo() {
            return BindInfo;
        }

        public void setBindInfo(List<?> BindInfo) {
            this.BindInfo = BindInfo;
        }

        public static class TaobaoKeInfoBean implements Serializable {
            private String weixin;
            private String taobaoKeCode;
            private String qq;

            public String getWeixin() {
                return weixin;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }

            public String getTaobaoKeCode() {
                return taobaoKeCode;
            }

            public void setTaobaoKeCode(String taobaoKeCode) {
                this.taobaoKeCode = taobaoKeCode;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }
        }
    }
}
