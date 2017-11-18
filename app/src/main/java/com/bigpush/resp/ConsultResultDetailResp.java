package com.bigpush.resp;

import java.io.Serializable;
import java.util.List;

public class ConsultResultDetailResp implements Serializable {


    /**
     * Data : {"createTime":"2017-10-22 03:43:03","clickCount":45,"infoCode":"9d41d853e90ddd513eb6d221fefb6ffc","content":[{"picUrl":"https://tp-qnam.smzdm.com/201710/20/59e9b81d9eca07512.jpg_e600.jpg","type":"image","text":"以上的说辞，估计大伙也知道当时我有多小白了吧！毕竟当时各种音染惯坏了，过于平淡下意识就会觉得音质不行，换作现在我还真不会这么说。耳机到手两年多了，该开的声都打开了，煲的没有在进步的可能了。回过头来听，在跟我说 HD598 均衡，我只能抛给你个大白眼。监听耳机的实名就是纯净的还原所有细节，虽然 DT 990 Pro 不是顶级的监听，但是既然是这个定位，那就应该尽量向这个方向去做，DT 990 Pro 还真的是努力了。三频均衡的不像话（跟我手头的所有耳机对比...），HD598 如果给你的感觉是纯净的均衡，那么这款耳机给你的就是纯粹的均衡，完全没有音染（虽说对比 HD598 稍微不太公平，毕竟定位不一样，但是当年两款耳机是差不多价位的）。要说这个耳机挑不挑前端，这么大的阻抗怎么会不需要好一点的前端呢？目前我只用电脑或者 HM603 来听DT990 Pro，Classic 也是会用到的，毕竟耳机都煲开了，声音不会太差，但是如果用手机推的话还是会觉得像是缺肉一样，相当骨感，齿音全出来了，所以还是需要大点功率的前端的。"},{"text":"以上的说辞，估计大伙也知道当时我有多小白了吧！毕竟当时各种音染惯坏了，过于平淡下意识就会觉得音质不行，换作现在我还真不会这么说。耳机到手两年多了，该开的声都打开了，煲的没有在进步的可能了。回过头来听，在跟我说 HD598 均衡，我只能抛给你个大白眼。监听耳机的实名就是纯净的还原所有细节，虽然 DT 990 Pro 不是顶级的监听，但是既然是这个定位，那就应该尽量向这个方向去做，DT 990 Pro 还真的是努力了。三频均衡的不像话（跟我手头的所有耳机对比...），HD598 如果给你的感觉是纯净的均衡，那么这款耳机给你的就是纯粹的均衡，完全没有音染（虽说对比 HD598 稍微不太公平，毕竟定位不一样，但是当年两款耳机是差不多价位的）。要说这个耳机挑不挑前端，这么大的阻抗怎么会不需要好一点的前端呢？目前我只用电脑或者 HM603 来听DT990 Pro，Classic 也是会用到的，毕竟耳机都煲开了，声音不会太差，但是如果用手机推的话还是会觉得像是缺肉一样，相当骨感，齿音全出来了，所以还是需要大点功率的前端的。","type":"text"}],"subTitle":"具有优秀性价比的耳机","title":"具有优秀性价比的耳机","image2":"https://tp-qnam.smzdm.com/201710/20/59e9b81d9eca07512.jpg_e600.jpg","image1":"https://tp-qnam.smzdm.com/201710/20/59e9b81d9eca07512.jpg_e600.jpg","okCount":158,"name":"淘宝客00001","synopsis":"具有优秀性价比的耳机具有优秀性价比的耳机"}
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
         * createTime : 2017-10-22 03:43:03
         * clickCount : 45
         * infoCode : 9d41d853e90ddd513eb6d221fefb6ffc
         * content : [{"picUrl":"https://tp-qnam.smzdm.com/201710/20/59e9b81d9eca07512.jpg_e600.jpg","type":"image"},{"text":"以上的说辞，估计大伙也知道当时我有多小白了吧！毕竟当时各种音染惯坏了，过于平淡下意识就会觉得音质不行，换作现在我还真不会这么说。耳机到手两年多了，该开的声都打开了，煲的没有在进步的可能了。回过头来听，在跟我说 HD598 均衡，我只能抛给你个大白眼。监听耳机的实名就是纯净的还原所有细节，虽然 DT 990 Pro 不是顶级的监听，但是既然是这个定位，那就应该尽量向这个方向去做，DT 990 Pro 还真的是努力了。三频均衡的不像话（跟我手头的所有耳机对比...），HD598 如果给你的感觉是纯净的均衡，那么这款耳机给你的就是纯粹的均衡，完全没有音染（虽说对比 HD598 稍微不太公平，毕竟定位不一样，但是当年两款耳机是差不多价位的）。要说这个耳机挑不挑前端，这么大的阻抗怎么会不需要好一点的前端呢？目前我只用电脑或者 HM603 来听DT990 Pro，Classic 也是会用到的，毕竟耳机都煲开了，声音不会太差，但是如果用手机推的话还是会觉得像是缺肉一样，相当骨感，齿音全出来了，所以还是需要大点功率的前端的。","type":"text"}]
         * subTitle : 具有优秀性价比的耳机
         * title : 具有优秀性价比的耳机
         * image2 : https://tp-qnam.smzdm.com/201710/20/59e9b81d9eca07512.jpg_e600.jpg
         * image1 : https://tp-qnam.smzdm.com/201710/20/59e9b81d9eca07512.jpg_e600.jpg
         * okCount : 158
         * name : 淘宝客00001
         * synopsis : 具有优秀性价比的耳机具有优秀性价比的耳机
         */

        private String createTime;
        private int clickCount;
        private String infoCode;
        private String subTitle;
        private String title;
        private String image2;
        private String image1;
        private int okCount;
        private String name;
        private String synopsis;
        private List<ContentBean> content;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getClickCount() {
            return clickCount;
        }

        public void setClickCount(int clickCount) {
            this.clickCount = clickCount;
        }

        public String getInfoCode() {
            return infoCode;
        }

        public void setInfoCode(String infoCode) {
            this.infoCode = infoCode;
        }

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

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public int getOkCount() {
            return okCount;
        }

        public void setOkCount(int okCount) {
            this.okCount = okCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * picUrl : https://tp-qnam.smzdm.com/201710/20/59e9b81d9eca07512.jpg_e600.jpg
             * type : image
             * text : 以上的说辞，估计大伙也知道当时我有多小白了吧！毕竟当时各种音染惯坏了，过于平淡下意识就会觉得音质不行，换作现在我还真不会这么说。耳机到手两年多了，该开的声都打开了，煲的没有在进步的可能了。回过头来听，在跟我说 HD598 均衡，我只能抛给你个大白眼。监听耳机的实名就是纯净的还原所有细节，虽然 DT 990 Pro 不是顶级的监听，但是既然是这个定位，那就应该尽量向这个方向去做，DT 990 Pro 还真的是努力了。三频均衡的不像话（跟我手头的所有耳机对比...），HD598 如果给你的感觉是纯净的均衡，那么这款耳机给你的就是纯粹的均衡，完全没有音染（虽说对比 HD598 稍微不太公平，毕竟定位不一样，但是当年两款耳机是差不多价位的）。要说这个耳机挑不挑前端，这么大的阻抗怎么会不需要好一点的前端呢？目前我只用电脑或者 HM603 来听DT990 Pro，Classic 也是会用到的，毕竟耳机都煲开了，声音不会太差，但是如果用手机推的话还是会觉得像是缺肉一样，相当骨感，齿音全出来了，所以还是需要大点功率的前端的。
             */

            private String picUrl;
            private String type;
            private String text;

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
