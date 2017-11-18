package com.bigpush.resp;

import java.io.Serializable;
import java.util.List;

public class SysMsgResp implements Serializable {


    /**
     * Data : [{"createTime":"2017-10-22 01:31:03","subTitle":"测试文本副标题","dataType":"text","title":"测试文本标题","extend":{"text":"测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题"}},{"subTitle":"测试资讯副标题","dataType":"info","title":"测试资讯标题","extend":{"content":[{"text":"中国境内今天科技行业投融资总额约0人民币。海外科技行业的投融资总额约1911.93万美元，单笔最大交易事件发生在智能硬件行业，获得融资的企业为LeapMind，交易金额高达1000万美元。从领域分布上看，智能硬件占总投融资额的89.54%，约1711.93万美元，其次分别是购物（10.46%，约200万美元）。据钛媒体Pro分析，从地区分布上看，日本是今天投融资交易最多的国家，有 1 起，总额约1000万美元，第二第三名分别是奥地利（1 起，约711.93万美元）和新加坡（1 起，约200万美元）。","type":"text"},{"picUrl":"http://images.tmtpost.com/uploads/filetemp/2017/10/21/a59fd5ddc8ad38dd548fa915eaad63fb15085890030048.jpg","type":"image"},{"type":"commodity","row":{"campaignId":"30242704","numId":"546042194915","couponRate":14,"onlines":29,"couponPrice":5,"isVideo":"0","title":"ESK 苹果充电器iPhone6充电头6s手机7P快充1A苹果通用充电器套装","isGoldServ":"0","couponAfterPrice":30,"commission":7.6,"commissionType":2,"juEndTime":"2017-10-23 23:59:59","picUrl":"https://img.alicdn.com/imgextra/i2/690794401/TB2K3SMhrlmpuFjSZFlXXbdQXXa_!!690794401.jpg","clickCount":0,"videoUrl":"","juStartTime":"2017-10-17 00:00:00","couponId":"32accbb50b9a4206bcef34706ae60b23","intro":"芯合金数据线，充电稳定，闪电传输，编织线材，铝材外壳，安全保障，经久耐用","isOverseas":"0","isJuBuying":"0","commissionRate":2530,"shopType":"B","price":35,"shortTitle":"苹果6充电头6s手机充电器套装","sellerId":"690794401","volume":176,"introPhoto":"http://img.wntaoke.com/wenan/ysd_546042194915.jpg","shopkeeperId":"26739645","isQiang":"0","typeId":2,"commodityCode":"73F6A45045EB0C32022C1C4781820B1C","addTime":"2017-10-17 19:14:32"}}],"createTime":"2017-10-22 01:31:03","subTitle":"钛媒体的机器人钛博士今天记录了6起重要投融资事件，总计交易金额超过1.29亿元。","image2":"http://images.tmtpost.com/uploads/images/2017/10/apple-desk-laptop-macbook.jpg","title":"钛媒体Pro创投日报：10月21日收录投融资项目6起1277","image1":"http://images.tmtpost.com/uploads/images/2017/10/apple-desk-laptop-macbook.jpg","clickCount":13,"okCount":0,"name":"淘宝客00001","synopsis":"2017年10月21日，截至今日21点，钛博士机器人侦测到 6 起发生在科技和互联网行业的投融资或并购事件，其中 0 起发生在中国境内，6 起发生在海外，总计交易额超过1.29亿人民币","infoCode":"924a6baaa01552faabbcde394609e98c"}},{"createTime":"2017-11-10 00:01:01","subTitle":"测试商品副标题","dataType":"commodity","title":"测试商品标题","extend":{"campaignId":"67745997","numId":"541509305908","couponRate":33,"onlines":0,"couponPrice":10,"isVideo":"0","title":"儿童情绪管理与性格培养绘本3-6周岁幼儿情商绘本故事书籍读物 好性格绘本 宝宝有声绘本故事书0-3岁早教2-3岁幼儿园幼儿绘本3-6岁","isGoldServ":"0","couponAfterPrice":19.9,"commission":6.1,"commissionType":4,"juEndTime":"2017-11-07 23:17:05","picUrl":"https://img.alicdn.com/imgextra/i3/26636953/TB2lfAFgvBNTKJjy1zdXXaScpXa_!!26636953.jpg","clickCount":1,"videoUrl":"","juStartTime":"2017-11-07 23:17:05","couponId":"533d5bd2a0bd44af99ec4aef54031989","intro":"立欣儿童关键期人格培养系列绘本，好品格决定好未来，好情商更胜高智商，内容丰富有趣，图画理解更直接，更敏感，健康阅读！","isOverseas":"0","isJuBuying":"0","commissionRate":3050,"shopType":"B","price":29.9,"shortTitle":"立欣儿童情绪管理与性格培养绘本","sellerId":"1751831526","volume":553,"introPhoto":"http://img.wntaoke.com/wenan/ysd_541509305908.jpg","shopkeeperId":"45568530","isQiang":"0","typeId":12,"commodityCode":"B4870F0A184987C1D754EDE1544A4B4C","addTime":"2017-11-07 23:17:05"}}]
     * status : true
     */

    private boolean status;
    private List<DataBean> Data;

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
         * createTime : 2017-10-22 01:31:03
         * subTitle : 测试文本副标题
         * dataType : text
         * title : 测试文本标题
         * extend : {"text":"测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题"}
         */

        private String createTime;
        private String subTitle;
        private String dataType;
        private String title;
        private String infoCode;

        public String getInfoCode() {
            return infoCode;
        }

        public void setInfoCode(String infoCode) {
            this.infoCode = infoCode;
        }

        private ExtendBean extend;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ExtendBean getExtend() {
            return extend;
        }

        public void setExtend(ExtendBean extend) {
            this.extend = extend;
        }

        public static class ExtendBean {
            /**
             * text : 测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题测试文本标题
             */

            private String text;
            /**
             * campaignId : 67745997
             * numId : 541509305908
             * couponRate : 33
             * onlines : 0
             * couponPrice : 10
             * isVideo : 0
             * title : 儿童情绪管理与性格培养绘本3-6周岁幼儿情商绘本故事书籍读物 好性格绘本 宝宝有声绘本故事书0-3岁早教2-3岁幼儿园幼儿绘本3-6岁
             * isGoldServ : 0
             * couponAfterPrice : 19.9
             * commission : 6.1
             * commissionType : 4
             * juEndTime : 2017-11-07 23:17:05
             * picUrl : https://img.alicdn.com/imgextra/i3/26636953/TB2lfAFgvBNTKJjy1zdXXaScpXa_!!26636953.jpg
             * clickCount : 1
             * videoUrl :
             * juStartTime : 2017-11-07 23:17:05
             * couponId : 533d5bd2a0bd44af99ec4aef54031989
             * intro : 立欣儿童关键期人格培养系列绘本，好品格决定好未来，好情商更胜高智商，内容丰富有趣，图画理解更直接，更敏感，健康阅读！
             * isOverseas : 0
             * isJuBuying : 0
             * commissionRate : 3050
             * shopType : B
             * price : 29.9
             * shortTitle : 立欣儿童情绪管理与性格培养绘本
             * sellerId : 1751831526
             * volume : 553
             * introPhoto : http://img.wntaoke.com/wenan/ysd_541509305908.jpg
             * shopkeeperId : 45568530
             * isQiang : 0
             * typeId : 12
             * commodityCode : B4870F0A184987C1D754EDE1544A4B4C
             * addTime : 2017-11-07 23:17:05
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
            private String infoCode;
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

            public String getInfoCode() {
                return infoCode;
            }

            public void setInfoCode(String infoCode) {
                this.infoCode = infoCode;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

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
