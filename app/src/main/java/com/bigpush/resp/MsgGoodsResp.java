package com.bigpush.resp;

import java.util.List;

public class MsgGoodsResp {

    /**
     * Data : [{"title":"消息测试3","time":"2017-10-17 22:00:00","extend":{"campaignId":"48928862","numId":"555008637809","couponRate":53,"onlines":7,"couponPrice":10,"isVideo":"0","title":"卡通皮卡丘可爱萌宠oppor11/r9/r9s/plus手机壳a57男女款硅胶防摔","isGoldServ":"1","couponAfterPrice":8.8,"commission":2.2,"commissionType":4,"juEndTime":"2017-10-16 17:28:31","picUrl":"https://gd4.alicdn.com/imgextra/i4/2509010429/TB2nIXowC0jpuFjy0FlXXc0bpXa_!!2509010429.jpg","clickCount":14,"videoUrl":"","juStartTime":"2017-10-16 17:28:31","couponId":"5f82f5e14d404200a61d0b38efe6d31e","intro":"正版授权，皮卡丘一体式指环扣支架，360°旋转，时尚创意，超好看，超萌的皮卡丘，无处不在的快乐，带给您更多的惊喜","isOverseas":"0","isJuBuying":"0","commissionRate":2500,"shopType":"C","price":18.8,"shortTitle":"卡通皮卡丘可爱萌宠oppor11手机壳","sellerId":"2509010429","volume":362,"introPhoto":"http://img.wntaoke.com/wenan/ysd_555008637809.jpg","shopkeeperId":"111298696","isQiang":"0","typeId":4,"commodityCode":"29a496fa74f9c2b4d97c73e71b16c76e","addTime":"2017-10-16 17:28:31"},"type":"commodity"}]
     * status : 1
     */

    private String status;
    private List<DataBean> Data;

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
         * title : 消息测试3
         * time : 2017-10-17 22:00:00
         * extend : {"campaignId":"48928862","numId":"555008637809","couponRate":53,"onlines":7,"couponPrice":10,"isVideo":"0","title":"卡通皮卡丘可爱萌宠oppor11/r9/r9s/plus手机壳a57男女款硅胶防摔","isGoldServ":"1","couponAfterPrice":8.8,"commission":2.2,"commissionType":4,"juEndTime":"2017-10-16 17:28:31","picUrl":"https://gd4.alicdn.com/imgextra/i4/2509010429/TB2nIXowC0jpuFjy0FlXXc0bpXa_!!2509010429.jpg","clickCount":14,"videoUrl":"","juStartTime":"2017-10-16 17:28:31","couponId":"5f82f5e14d404200a61d0b38efe6d31e","intro":"正版授权，皮卡丘一体式指环扣支架，360°旋转，时尚创意，超好看，超萌的皮卡丘，无处不在的快乐，带给您更多的惊喜","isOverseas":"0","isJuBuying":"0","commissionRate":2500,"shopType":"C","price":18.8,"shortTitle":"卡通皮卡丘可爱萌宠oppor11手机壳","sellerId":"2509010429","volume":362,"introPhoto":"http://img.wntaoke.com/wenan/ysd_555008637809.jpg","shopkeeperId":"111298696","isQiang":"0","typeId":4,"commodityCode":"29a496fa74f9c2b4d97c73e71b16c76e","addTime":"2017-10-16 17:28:31"}
         * type : commodity
         */

        private String title;
        private String time;
        private ExtendBean extend;
        private String type;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public ExtendBean getExtend() {
            return extend;
        }

        public void setExtend(ExtendBean extend) {
            this.extend = extend;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class ExtendBean {
            /**
             * campaignId : 48928862
             * numId : 555008637809
             * couponRate : 53
             * onlines : 7
             * couponPrice : 10
             * isVideo : 0
             * title : 卡通皮卡丘可爱萌宠oppor11/r9/r9s/plus手机壳a57男女款硅胶防摔
             * isGoldServ : 1
             * couponAfterPrice : 8.8
             * commission : 2.2
             * commissionType : 4
             * juEndTime : 2017-10-16 17:28:31
             * picUrl : https://gd4.alicdn.com/imgextra/i4/2509010429/TB2nIXowC0jpuFjy0FlXXc0bpXa_!!2509010429.jpg
             * clickCount : 14
             * videoUrl :
             * juStartTime : 2017-10-16 17:28:31
             * couponId : 5f82f5e14d404200a61d0b38efe6d31e
             * intro : 正版授权，皮卡丘一体式指环扣支架，360°旋转，时尚创意，超好看，超萌的皮卡丘，无处不在的快乐，带给您更多的惊喜
             * isOverseas : 0
             * isJuBuying : 0
             * commissionRate : 2500
             * shopType : C
             * price : 18.8
             * shortTitle : 卡通皮卡丘可爱萌宠oppor11手机壳
             * sellerId : 2509010429
             * volume : 362
             * introPhoto : http://img.wntaoke.com/wenan/ysd_555008637809.jpg
             * shopkeeperId : 111298696
             * isQiang : 0
             * typeId : 4
             * commodityCode : 29a496fa74f9c2b4d97c73e71b16c76e
             * addTime : 2017-10-16 17:28:31
             */

            private String campaignId;
            private String numId;
            private int couponRate;
            private int onlines;
            private int couponPrice;
            private String isVideo;
            private String title;
            private String isGoldServ;
            private double couponAfterPrice;
            private double commission;
            private int commissionType;
            private String juEndTime;
            private String picUrl;
            private int clickCount;
            private String videoUrl;
            private String juStartTime;
            private String couponId;
            private String intro;
            private String isOverseas;
            private String isJuBuying;
            private int commissionRate;
            private String shopType;
            private double price;
            private String shortTitle;
            private String sellerId;
            private int volume;
            private String introPhoto;
            private String shopkeeperId;
            private String isQiang;
            private int typeId;
            private String commodityCode;
            private String addTime;

            public String getCampaignId() {
                return campaignId;
            }

            public void setCampaignId(String campaignId) {
                this.campaignId = campaignId;
            }

            public String getNumId() {
                return numId;
            }

            public void setNumId(String numId) {
                this.numId = numId;
            }

            public int getCouponRate() {
                return couponRate;
            }

            public void setCouponRate(int couponRate) {
                this.couponRate = couponRate;
            }

            public int getOnlines() {
                return onlines;
            }

            public void setOnlines(int onlines) {
                this.onlines = onlines;
            }

            public int getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(int couponPrice) {
                this.couponPrice = couponPrice;
            }

            public String getIsVideo() {
                return isVideo;
            }

            public void setIsVideo(String isVideo) {
                this.isVideo = isVideo;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIsGoldServ() {
                return isGoldServ;
            }

            public void setIsGoldServ(String isGoldServ) {
                this.isGoldServ = isGoldServ;
            }

            public double getCouponAfterPrice() {
                return couponAfterPrice;
            }

            public void setCouponAfterPrice(double couponAfterPrice) {
                this.couponAfterPrice = couponAfterPrice;
            }

            public double getCommission() {
                return commission;
            }

            public void setCommission(double commission) {
                this.commission = commission;
            }

            public int getCommissionType() {
                return commissionType;
            }

            public void setCommissionType(int commissionType) {
                this.commissionType = commissionType;
            }

            public String getJuEndTime() {
                return juEndTime;
            }

            public void setJuEndTime(String juEndTime) {
                this.juEndTime = juEndTime;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getClickCount() {
                return clickCount;
            }

            public void setClickCount(int clickCount) {
                this.clickCount = clickCount;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public String getJuStartTime() {
                return juStartTime;
            }

            public void setJuStartTime(String juStartTime) {
                this.juStartTime = juStartTime;
            }

            public String getCouponId() {
                return couponId;
            }

            public void setCouponId(String couponId) {
                this.couponId = couponId;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getIsOverseas() {
                return isOverseas;
            }

            public void setIsOverseas(String isOverseas) {
                this.isOverseas = isOverseas;
            }

            public String getIsJuBuying() {
                return isJuBuying;
            }

            public void setIsJuBuying(String isJuBuying) {
                this.isJuBuying = isJuBuying;
            }

            public int getCommissionRate() {
                return commissionRate;
            }

            public void setCommissionRate(int commissionRate) {
                this.commissionRate = commissionRate;
            }

            public String getShopType() {
                return shopType;
            }

            public void setShopType(String shopType) {
                this.shopType = shopType;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getShortTitle() {
                return shortTitle;
            }

            public void setShortTitle(String shortTitle) {
                this.shortTitle = shortTitle;
            }

            public String getSellerId() {
                return sellerId;
            }

            public void setSellerId(String sellerId) {
                this.sellerId = sellerId;
            }

            public int getVolume() {
                return volume;
            }

            public void setVolume(int volume) {
                this.volume = volume;
            }

            public String getIntroPhoto() {
                return introPhoto;
            }

            public void setIntroPhoto(String introPhoto) {
                this.introPhoto = introPhoto;
            }

            public String getShopkeeperId() {
                return shopkeeperId;
            }

            public void setShopkeeperId(String shopkeeperId) {
                this.shopkeeperId = shopkeeperId;
            }

            public String getIsQiang() {
                return isQiang;
            }

            public void setIsQiang(String isQiang) {
                this.isQiang = isQiang;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(String commodityCode) {
                this.commodityCode = commodityCode;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }
        }
    }
}
