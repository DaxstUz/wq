package com.bigpush.resp;

import java.io.Serializable;
import java.util.List;

public class ConsultRecItemResp {

    /**
     * Data : [{"type":"item","row":{"icon":"http://47.95.220.122/public/icon/2.jpg","subTitle":"超便宜白菜价","title":"哇券日报","itemCode":"infoItemDayNew"}},{"type":"item","row":{"icon":"http://47.95.220.122/public/icon/1.jpg","subTitle":"每日人气精选","title":"哇券精选","itemCode":"infoItemChoice"}}]
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
         * row : {"icon":"http://47.95.220.122/public/icon/2.jpg","subTitle":"超便宜白菜价","title":"哇券日报","itemCode":"infoItemDayNew"}
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

        public static class RowBean implements Serializable {
            /**
             * icon : http://47.95.220.122/public/icon/2.jpg
             * subTitle : 超便宜白菜价
             * title : 哇券日报
             * itemCode : infoItemDayNew
             */

            private String icon;
            private String subTitle;
            private String title;
            private String itemCode;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
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

            public String getItemCode() {
                return itemCode;
            }

            public void setItemCode(String itemCode) {
                this.itemCode = itemCode;
            }
        }
    }
}
