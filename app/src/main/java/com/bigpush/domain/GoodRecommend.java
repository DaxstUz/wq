package com.bigpush.domain;

import java.io.Serializable;

public class GoodRecommend implements Serializable {


    /**
     * type : commodity
     * row : {"campaignId":"64935825","couponRate":26,"numId":"553529428300","onlines":96,"couponPrice":10,"isVideo":"0","title":"天天特价 稻田村酸奶溶豆 婴儿辅食溶豆豆宝宝零食水果小溶豆2盒","isGoldServ":"0","couponAfterPrice":27.5,"commission":6.9,"commissionType":4,"juEndTime":"2017-10-15 23:59:59","picUrl":"http://img.alicdn.com/imgextra/i2/23280569/TB2qgtEXC3PL1JjSZFtXXclRVXa_!!23280569.jpg","clickCount":0,"videoUrl":"","juStartTime":"2017-10-08 00:00:00","couponId":"f9f09ee8c4d547cd87b957c86f735fa3","intro":"稻田村溶豆豆，宝宝零食婴儿辅食，溶溶豆儿童食品酸奶小溶豆，稻田村加锌益生菌酸奶小溶豆，超值两盒装。","isOverseas":"0","isJuBuying":"0","commissionRate":2500,"shopType":"C","price":37.5,"shortTitle":"稻田村酸奶小溶豆超值2盒装","sellerId":"23280569","volume":657,"introPhoto":"http://img.wntaoke.com/wenan/ysd_553529428300.jpg","shopkeeperId":"16084714","typeId":4,"isQiang":"0","commodityCode":"73858d998645bac1848d7d94ceb1e87c","addTime":"2017-10-08 12:40:49"}
     */

    private String type;
    private GoodsDetail row;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GoodsDetail getRow() {
        return row;
    }

    public void setRow(GoodsDetail row) {
        this.row = row;
    }
}
