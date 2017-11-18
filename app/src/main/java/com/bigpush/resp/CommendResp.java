package com.bigpush.resp;

public class CommendResp {

    /**
     * Data : {"text":" 【原价59.0元】券后￥29.0元
     【优惠券】￥30.0元
     【链接】http://url.sir66.com/Y7fiue
     【淘口令】￥VFbk0gTleRU￥
     复制文案后，打开淘宝APP即可进入商品详情页","title":"2017秋冬新款纯棉半高领打底衫长袖黑白条纹T恤修身原宿女装上衣","price":29,"numCode":"123456","keyWords":"￥VFbk0gTleRU￥","pic":"https://gd3.alicdn.com/imgextra/i3/766604588/TB27xgGe.3IL1JjSZFMXXajrFXa_!!766604588.jpg","commodityCode":"4FFFC70C187F7DC7B33134B4EFA1C0DE","url":"http://url.sir66.com/Y7fiue"}
     * status : 1
     */

    private DataBean Data;
    private int status;

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
         * text :
         * title : 2017秋冬新款纯棉半高领打底衫长袖黑白条纹T恤修身原宿女装上衣
         * price : 29
         * numCode : 123456
         * keyWords : ￥VFbk0gTleRU￥
         * pic : https://gd3.alicdn.com/imgextra/i3/766604588/TB27xgGe.3IL1JjSZFMXXajrFXa_!!766604588.jpg
         * commodityCode : 4FFFC70C187F7DC7B33134B4EFA1C0DE
         * url : http://url.sir66.com/Y7fiue
         */

        private String text;
        private String title;
        private int price;
        private String numCode;
        private String keyWords;
        private String pic;
        private String commodityCode;
        private String url;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getNumCode() {
            return numCode;
        }

        public void setNumCode(String numCode) {
            this.numCode = numCode;
        }

        public String getKeyWords() {
            return keyWords;
        }

        public void setKeyWords(String keyWords) {
            this.keyWords = keyWords;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCommodityCode() {
            return commodityCode;
        }

        public void setCommodityCode(String commodityCode) {
            this.commodityCode = commodityCode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
