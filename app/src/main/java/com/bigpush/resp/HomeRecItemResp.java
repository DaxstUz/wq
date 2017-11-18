package com.bigpush.resp;

import java.io.Serializable;
import java.util.List;

public class HomeRecItemResp implements Serializable {


    /**
     * Data : [{"type":"item","row":{"icon":"http://47.95.220.122/public/icon/qiang.png","subTitle":null,"title":"必抢神劵","itemCode":"juan"}},{"type":"item","row":{"icon":"http://47.95.220.122/public/icon/ju.png","subTitle":null,"title":"聚划算","itemCode":"juHuaSuan"}},{"type":"item","row":{"icon":"http://47.95.220.122/public/icon/new.png","subTitle":null,"title":"今日上新","itemCode":"dayNew"}},{"type":"item","row":{"icon":"http://47.95.220.122/public/icon/hot.png","subTitle":null,"title":"超值人气","itemCode":"overflow"}},{"type":"item","row":{"icon":"http://47.95.220.122/public/icon/video.png","subTitle":null,"title":"视频购物","itemCode":"vodShopping"}}]
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

    public static class DataBean implements Serializable{
        /**
         * type : item
         * row : {"icon":"http://47.95.220.122/public/icon/qiang.png","subTitle":null,"title":"必抢神劵","itemCode":"juan"}
         */

        private String type;
        private RowBean row;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public RowBean getRow() {
            return row;
        }

        public void setRow(RowBean row) {
            this.row = row;
        }

        public static class RowBean  implements  Serializable {
            /**
             * icon : http://47.95.220.122/public/icon/qiang.png
             * subTitle : null
             * title : 必抢神劵
             * itemCode : juan
             */

            private String icon;
            private Object subTitle;
            private String title;
            private String itemCode;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(Object subTitle) {
                this.subTitle = subTitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getItemCode() {
                return itemCode;
            }

            public void setItemCode(String itemCode) {
                this.itemCode = itemCode;
            }
        }
    }
}
