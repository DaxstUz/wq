package com.bigpush.resp;

import java.io.Serializable;
import java.util.List;

public class HomeBannerResp implements Serializable {

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
        private String subTitle;
        private String title;
        private String image;
        private String type;
        private RowBean row;

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBean.RowBean getRow() {
            return row;
        }

        public void setRow(DataBean.RowBean row) {
            this.row = row;
        }

        public static class RowBean {
            private String campaignId;
            private String infoCode;
            private int couponRate;
            private String numId;
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
            private int typeId;
            private String isQiang;
            private String commodityCode;
            private String addTime;

            public String getInfoCode() {
                return infoCode;
            }

            public void setInfoCode(String infoCode) {
                this.infoCode = infoCode;
            }

            public String getCampaignId() {
                return campaignId;
            }

            public void setCampaignId(String campaignId) {
                this.campaignId = campaignId;
            }

            public int getCouponRate() {
                return couponRate;
            }

            public void setCouponRate(int couponRate) {
                this.couponRate = couponRate;
            }

            public String getNumId() {
                return numId;
            }

            public void setNumId(String numId) {
                this.numId = numId;
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

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getIsQiang() {
                return isQiang;
            }

            public void setIsQiang(String isQiang) {
                this.isQiang = isQiang;
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
