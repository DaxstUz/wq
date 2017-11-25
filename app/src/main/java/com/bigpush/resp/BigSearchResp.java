package com.bigpush.resp;

import java.io.Serializable;
import java.util.List;

public class BigSearchResp implements Serializable {

    private DataBean data;
    private InfoBean info;
    private boolean ok;
    private Object invalidKey;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Object getInvalidKey() {
        return invalidKey;
    }

    public void setInvalidKey(Object invalidKey) {
        this.invalidKey = invalidKey;
    }

    public static class DataBean {
        private HeadBean head;
        private ConditionBean condition;
        private PaginatorBean paginator;
        private Object extraInfo;
        private List<PageListBean> pageList;
        private List<NavigatorBean> navigator;

        public HeadBean getHead() {
            return head;
        }

        public void setHead(HeadBean head) {
            this.head = head;
        }

        public ConditionBean getCondition() {
            return condition;
        }

        public void setCondition(ConditionBean condition) {
            this.condition = condition;
        }

        public PaginatorBean getPaginator() {
            return paginator;
        }

        public void setPaginator(PaginatorBean paginator) {
            this.paginator = paginator;
        }

        public Object getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(Object extraInfo) {
            this.extraInfo = extraInfo;
        }

        public List<PageListBean> getPageList() {
            return pageList;
        }

        public void setPageList(List<PageListBean> pageList) {
            this.pageList = pageList;
        }

        public List<NavigatorBean> getNavigator() {
            return navigator;
        }

        public void setNavigator(List<NavigatorBean> navigator) {
            this.navigator = navigator;
        }

        public static class HeadBean {
            /**
             * version : 1.0
             * status : OK
             * pageNo : 1
             * pageSize : 10
             * searchUrl : null
             * pvid : 100_10.103.66.139_23798_461510151381497189
             * errmsg : null
             * fromcache : null
             * processtime : 46272
             * ha3time : 42988
             * docsfound : 19865
             * docsreturn : 10
             * responseTxt : null
             */

            private String version;
            private String status;
            private int pageNo;
            private int pageSize;
            private Object searchUrl;
            private String pvid;
            private Object errmsg;
            private Object fromcache;
            private int processtime;
            private int ha3time;
            private int docsfound;
            private int docsreturn;
            private Object responseTxt;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public Object getSearchUrl() {
                return searchUrl;
            }

            public void setSearchUrl(Object searchUrl) {
                this.searchUrl = searchUrl;
            }

            public String getPvid() {
                return pvid;
            }

            public void setPvid(String pvid) {
                this.pvid = pvid;
            }

            public Object getErrmsg() {
                return errmsg;
            }

            public void setErrmsg(Object errmsg) {
                this.errmsg = errmsg;
            }

            public Object getFromcache() {
                return fromcache;
            }

            public void setFromcache(Object fromcache) {
                this.fromcache = fromcache;
            }

            public int getProcesstime() {
                return processtime;
            }

            public void setProcesstime(int processtime) {
                this.processtime = processtime;
            }

            public int getHa3time() {
                return ha3time;
            }

            public void setHa3time(int ha3time) {
                this.ha3time = ha3time;
            }

            public int getDocsfound() {
                return docsfound;
            }

            public void setDocsfound(int docsfound) {
                this.docsfound = docsfound;
            }

            public int getDocsreturn() {
                return docsreturn;
            }

            public void setDocsreturn(int docsreturn) {
                this.docsreturn = docsreturn;
            }

            public Object getResponseTxt() {
                return responseTxt;
            }

            public void setResponseTxt(Object responseTxt) {
                this.responseTxt = responseTxt;
            }
        }

        public static class ConditionBean {
            /**
             * userType : null
             * queryType : 0
             * sortType : 9
             * loc : null
             * startDsr : null
             * includeDxjh : null
             * auctionTag : null
             * jhs : null
             * hasUmpBonus : null
             * isBizActivity : null
             * subOeRule : null
             * auctionTagRaw : null
             * freeShipment : null
             * startTkRate : null
             * endTkRate : null
             * startTkTotalSales : null
             * startPrice : null
             * endPrice : null
             * startRatesum : null
             * endRatesum : null
             * startQuantity : null
             * startBiz30day : null
             * startPayUv30 : null
             * hPayRate30 : null
             * hGoodRate : null
             * lRfdRate : null
             * startSpay30 : null
             * hSellerGoodrat : null
             * hSpayRate30 : null
             * startRlRate : null
             * shopTag : null
             * npxType : null
             * picQuality : null
             * selectedNavigator : null
             * typeTagName : null
             */

            private Object userType;
            private int queryType;
            private int sortType;
            private Object loc;
            private Object startDsr;
            private Object includeDxjh;
            private Object auctionTag;
            private Object jhs;
            private Object hasUmpBonus;
            private Object isBizActivity;
            private Object subOeRule;
            private Object auctionTagRaw;
            private Object freeShipment;
            private Object startTkRate;
            private Object endTkRate;
            private Object startTkTotalSales;
            private Object startPrice;
            private Object endPrice;
            private Object startRatesum;
            private Object endRatesum;
            private Object startQuantity;
            private Object startBiz30day;
            private Object startPayUv30;
            private Object hPayRate30;
            private Object hGoodRate;
            private Object lRfdRate;
            private Object startSpay30;
            private Object hSellerGoodrat;
            private Object hSpayRate30;
            private Object startRlRate;
            private Object shopTag;
            private Object npxType;
            private Object picQuality;
            private Object selectedNavigator;
            private Object typeTagName;

            public Object getUserType() {
                return userType;
            }

            public void setUserType(Object userType) {
                this.userType = userType;
            }

            public int getQueryType() {
                return queryType;
            }

            public void setQueryType(int queryType) {
                this.queryType = queryType;
            }

            public int getSortType() {
                return sortType;
            }

            public void setSortType(int sortType) {
                this.sortType = sortType;
            }

            public Object getLoc() {
                return loc;
            }

            public void setLoc(Object loc) {
                this.loc = loc;
            }

            public Object getStartDsr() {
                return startDsr;
            }

            public void setStartDsr(Object startDsr) {
                this.startDsr = startDsr;
            }

            public Object getIncludeDxjh() {
                return includeDxjh;
            }

            public void setIncludeDxjh(Object includeDxjh) {
                this.includeDxjh = includeDxjh;
            }

            public Object getAuctionTag() {
                return auctionTag;
            }

            public void setAuctionTag(Object auctionTag) {
                this.auctionTag = auctionTag;
            }

            public Object getJhs() {
                return jhs;
            }

            public void setJhs(Object jhs) {
                this.jhs = jhs;
            }

            public Object getHasUmpBonus() {
                return hasUmpBonus;
            }

            public void setHasUmpBonus(Object hasUmpBonus) {
                this.hasUmpBonus = hasUmpBonus;
            }

            public Object getIsBizActivity() {
                return isBizActivity;
            }

            public void setIsBizActivity(Object isBizActivity) {
                this.isBizActivity = isBizActivity;
            }

            public Object getSubOeRule() {
                return subOeRule;
            }

            public void setSubOeRule(Object subOeRule) {
                this.subOeRule = subOeRule;
            }

            public Object getAuctionTagRaw() {
                return auctionTagRaw;
            }

            public void setAuctionTagRaw(Object auctionTagRaw) {
                this.auctionTagRaw = auctionTagRaw;
            }

            public Object getFreeShipment() {
                return freeShipment;
            }

            public void setFreeShipment(Object freeShipment) {
                this.freeShipment = freeShipment;
            }

            public Object getStartTkRate() {
                return startTkRate;
            }

            public void setStartTkRate(Object startTkRate) {
                this.startTkRate = startTkRate;
            }

            public Object getEndTkRate() {
                return endTkRate;
            }

            public void setEndTkRate(Object endTkRate) {
                this.endTkRate = endTkRate;
            }

            public Object getStartTkTotalSales() {
                return startTkTotalSales;
            }

            public void setStartTkTotalSales(Object startTkTotalSales) {
                this.startTkTotalSales = startTkTotalSales;
            }

            public Object getStartPrice() {
                return startPrice;
            }

            public void setStartPrice(Object startPrice) {
                this.startPrice = startPrice;
            }

            public Object getEndPrice() {
                return endPrice;
            }

            public void setEndPrice(Object endPrice) {
                this.endPrice = endPrice;
            }

            public Object getStartRatesum() {
                return startRatesum;
            }

            public void setStartRatesum(Object startRatesum) {
                this.startRatesum = startRatesum;
            }

            public Object getEndRatesum() {
                return endRatesum;
            }

            public void setEndRatesum(Object endRatesum) {
                this.endRatesum = endRatesum;
            }

            public Object getStartQuantity() {
                return startQuantity;
            }

            public void setStartQuantity(Object startQuantity) {
                this.startQuantity = startQuantity;
            }

            public Object getStartBiz30day() {
                return startBiz30day;
            }

            public void setStartBiz30day(Object startBiz30day) {
                this.startBiz30day = startBiz30day;
            }

            public Object getStartPayUv30() {
                return startPayUv30;
            }

            public void setStartPayUv30(Object startPayUv30) {
                this.startPayUv30 = startPayUv30;
            }

            public Object getHPayRate30() {
                return hPayRate30;
            }

            public void setHPayRate30(Object hPayRate30) {
                this.hPayRate30 = hPayRate30;
            }

            public Object getHGoodRate() {
                return hGoodRate;
            }

            public void setHGoodRate(Object hGoodRate) {
                this.hGoodRate = hGoodRate;
            }

            public Object getLRfdRate() {
                return lRfdRate;
            }

            public void setLRfdRate(Object lRfdRate) {
                this.lRfdRate = lRfdRate;
            }

            public Object getStartSpay30() {
                return startSpay30;
            }

            public void setStartSpay30(Object startSpay30) {
                this.startSpay30 = startSpay30;
            }

            public Object getHSellerGoodrat() {
                return hSellerGoodrat;
            }

            public void setHSellerGoodrat(Object hSellerGoodrat) {
                this.hSellerGoodrat = hSellerGoodrat;
            }

            public Object getHSpayRate30() {
                return hSpayRate30;
            }

            public void setHSpayRate30(Object hSpayRate30) {
                this.hSpayRate30 = hSpayRate30;
            }

            public Object getStartRlRate() {
                return startRlRate;
            }

            public void setStartRlRate(Object startRlRate) {
                this.startRlRate = startRlRate;
            }

            public Object getShopTag() {
                return shopTag;
            }

            public void setShopTag(Object shopTag) {
                this.shopTag = shopTag;
            }

            public Object getNpxType() {
                return npxType;
            }

            public void setNpxType(Object npxType) {
                this.npxType = npxType;
            }

            public Object getPicQuality() {
                return picQuality;
            }

            public void setPicQuality(Object picQuality) {
                this.picQuality = picQuality;
            }

            public Object getSelectedNavigator() {
                return selectedNavigator;
            }

            public void setSelectedNavigator(Object selectedNavigator) {
                this.selectedNavigator = selectedNavigator;
            }

            public Object getTypeTagName() {
                return typeTagName;
            }

            public void setTypeTagName(Object typeTagName) {
                this.typeTagName = typeTagName;
            }
        }

        public static class PaginatorBean {
            /**
             * length : 10
             * offset : 0
             * page : 1
             * beginIndex : 1
             * endIndex : 10
             * items : 19865
             * pages : 1987
             * itemsPerPage : 10
             * firstPage : 1
             * lastPage : 1987
             * previousPage : 1
             * nextPage : 2
             * slider : [1,2,3,4,5,6,7]
             */

            private int length;
            private int offset;
            private int page;
            private int beginIndex;
            private int endIndex;
            private int items;
            private int pages;
            private int itemsPerPage;
            private int firstPage;
            private int lastPage;
            private int previousPage;
            private int nextPage;
            private List<Integer> slider;

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getBeginIndex() {
                return beginIndex;
            }

            public void setBeginIndex(int beginIndex) {
                this.beginIndex = beginIndex;
            }

            public int getEndIndex() {
                return endIndex;
            }

            public void setEndIndex(int endIndex) {
                this.endIndex = endIndex;
            }

            public int getItems() {
                return items;
            }

            public void setItems(int items) {
                this.items = items;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getItemsPerPage() {
                return itemsPerPage;
            }

            public void setItemsPerPage(int itemsPerPage) {
                this.itemsPerPage = itemsPerPage;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public int getPreviousPage() {
                return previousPage;
            }

            public void setPreviousPage(int previousPage) {
                this.previousPage = previousPage;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public List<Integer> getSlider() {
                return slider;
            }

            public void setSlider(List<Integer> slider) {
                this.slider = slider;
            }
        }

        public static class PageListBean {
            /**
             * tkSpecialCampaignIdRateMap : {"4074438":"4.50"}
             * eventCreatorId : 0
             * rootCatId : 50002766
             * leafCatId : 123256001
             * debugInfo : null
             * rootCatScore : 0
             * nick : 天猫超市
             * userType : 1
             * title : 卫龙小面筋辣条280g辣味豆干80后怀旧麻辣<span class=H>零食</span>小吃
             * sellerId : 725677994
             * shopTitle : 天猫超市
             * pictUrl : //img.alicdn.com/bao/uploaded/i4/725677994/TB1nWgGaHsTMeJjy1zeXXcOCVXa_!!2-item_pic.png
             * auctionId : 44517810388
             * tkMktStatus : null
             * couponActivityId : null
             * reservePrice : 15
             * couponLeftCount : 0
             * couponTotalCount : 0
             * zkPrice : 15
             * couponAmount : 0
             * auctionUrl : http://item.taobao.com/item.htm?id=44517810388
             * biz30day : 233303
             * tkRate : 2
             * tk3rdRate : null
             * rlRate : 0
             * totalNum : 24163
             * totalFee : 4117.22
             * sameItemPid : -2147475985
             * hasRecommended : null
             * hasSame : null
             * tkCommFee : 0.3
             * couponLink :
             * couponLinkTaoToken :
             * dayLeft : -17478
             * includeDxjh : 1
             * auctionTag : 258 513 587 651 843 1163 1478 1483 2049 2059 2178 2562 3911 3974 4166 4491 4550 5895 6603 7046 8326 8390 11083 11339 12491 13707 13771 15755 16395 18059 19457 20545 28353 28802 30977 33217 34305 34369 35713 36161 37569 37633 37697 39233 39553 40897 51585 52737 59841 67521 73089 101762 104130 107842 112322 113666 151362 166402 170946 172866 174914 185474 197954 202434 205506 209090 219010 219074 219266 219394 219906 220674 220738 220802 220930
             * couponStartFee : 0
             * couponEffectiveStartTime :
             * couponEffectiveEndTime :
             * hasUmpBonus : null
             * umpBonus : null
             * isBizActivity : null
             * couponShortLink : null
             * couponInfo : 无
             * eventRate : null
             * rootCategoryName : null
             * couponOriLink : null
             * userTypeName : null
             */

//            private TkSpecialCampaignIdRateMapBean tkSpecialCampaignIdRateMap;
            private int eventCreatorId;
            private int rootCatId;
            private int leafCatId;
            private Object debugInfo;
            private int rootCatScore;
            private String nick;
            private int userType;
            private String title;
//            private Integer sellerId;
            private String shopTitle;
            private String pictUrl;
            private Long auctionId;
            private Object tkMktStatus;
            private Object couponActivityId;
            private double reservePrice;
            private int couponLeftCount;
            private int couponTotalCount;
            private double zkPrice;
            private double couponAmount;
            private String auctionUrl;
            private int biz30day;
            private int tkRate;
            private Object tk3rdRate;
            private int rlRate;
            private int totalNum;
            private double totalFee;
            private String sameItemPid;
            private Object hasRecommended;
            private Object hasSame;
            private double tkCommFee;
            private String couponLink;
            private String couponLinkTaoToken;
            private int dayLeft;
            private int includeDxjh;
            private String auctionTag;
            private int couponStartFee;
            private String couponEffectiveStartTime;
            private String couponEffectiveEndTime;
            private Object hasUmpBonus;
            private Object umpBonus;
            private Object isBizActivity;
            private Object couponShortLink;
            private String couponInfo;
            private Object eventRate;
            private Object rootCategoryName;
            private Object couponOriLink;
            private Object userTypeName;

//            public TkSpecialCampaignIdRateMapBean getTkSpecialCampaignIdRateMap() {
//                return tkSpecialCampaignIdRateMap;
//            }
//
//            public void setTkSpecialCampaignIdRateMap(TkSpecialCampaignIdRateMapBean tkSpecialCampaignIdRateMap) {
//                this.tkSpecialCampaignIdRateMap = tkSpecialCampaignIdRateMap;
//            }

            public int getEventCreatorId() {
                return eventCreatorId;
            }

            public void setEventCreatorId(int eventCreatorId) {
                this.eventCreatorId = eventCreatorId;
            }

            public int getRootCatId() {
                return rootCatId;
            }

            public void setRootCatId(int rootCatId) {
                this.rootCatId = rootCatId;
            }

            public int getLeafCatId() {
                return leafCatId;
            }

            public void setLeafCatId(int leafCatId) {
                this.leafCatId = leafCatId;
            }

            public Object getDebugInfo() {
                return debugInfo;
            }

            public void setDebugInfo(Object debugInfo) {
                this.debugInfo = debugInfo;
            }

            public int getRootCatScore() {
                return rootCatScore;
            }

            public void setRootCatScore(int rootCatScore) {
                this.rootCatScore = rootCatScore;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

//            public Integer getSellerId() {
//                return sellerId;
//            }
//
//            public void setSellerId(Integer sellerId) {
//                this.sellerId = sellerId;
//            }


            public double getReservePrice() {
                return reservePrice;
            }

            public void setReservePrice(double reservePrice) {
                this.reservePrice = reservePrice;
            }

            public double getZkPrice() {
                return zkPrice;
            }

            public void setZkPrice(double zkPrice) {
                this.zkPrice = zkPrice;
            }

            public double getCouponAmount() {
                return couponAmount;
            }

            public void setCouponAmount(double couponAmount) {
                this.couponAmount = couponAmount;
            }

            public String getShopTitle() {
                return shopTitle;
            }

            public void setShopTitle(String shopTitle) {
                this.shopTitle = shopTitle;
            }

            public String getPictUrl() {
                return pictUrl;
            }

            public void setPictUrl(String pictUrl) {
                this.pictUrl = pictUrl;
            }

            public Long getAuctionId() {
                return auctionId;
            }

            public void setAuctionId(Long auctionId) {
                this.auctionId = auctionId;
            }

            public Object getTkMktStatus() {
                return tkMktStatus;
            }

            public void setTkMktStatus(Object tkMktStatus) {
                this.tkMktStatus = tkMktStatus;
            }

            public Object getCouponActivityId() {
                return couponActivityId;
            }

            public void setCouponActivityId(Object couponActivityId) {
                this.couponActivityId = couponActivityId;
            }

            public int getCouponLeftCount() {
                return couponLeftCount;
            }

            public void setCouponLeftCount(int couponLeftCount) {
                this.couponLeftCount = couponLeftCount;
            }

            public int getCouponTotalCount() {
                return couponTotalCount;
            }

            public void setCouponTotalCount(int couponTotalCount) {
                this.couponTotalCount = couponTotalCount;
            }

            public String getAuctionUrl() {
                return auctionUrl;
            }

            public void setAuctionUrl(String auctionUrl) {
                this.auctionUrl = auctionUrl;
            }

            public int getBiz30day() {
                return biz30day;
            }

            public void setBiz30day(int biz30day) {
                this.biz30day = biz30day;
            }

            public int getTkRate() {
                return tkRate;
            }

            public void setTkRate(int tkRate) {
                this.tkRate = tkRate;
            }

            public Object getTk3rdRate() {
                return tk3rdRate;
            }

            public void setTk3rdRate(Object tk3rdRate) {
                this.tk3rdRate = tk3rdRate;
            }

            public int getRlRate() {
                return rlRate;
            }

            public void setRlRate(int rlRate) {
                this.rlRate = rlRate;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public double getTotalFee() {
                return totalFee;
            }

            public void setTotalFee(double totalFee) {
                this.totalFee = totalFee;
            }

            public String getSameItemPid() {
                return sameItemPid;
            }

            public void setSameItemPid(String sameItemPid) {
                this.sameItemPid = sameItemPid;
            }

            public Object getHasRecommended() {
                return hasRecommended;
            }

            public void setHasRecommended(Object hasRecommended) {
                this.hasRecommended = hasRecommended;
            }

            public Object getHasSame() {
                return hasSame;
            }

            public void setHasSame(Object hasSame) {
                this.hasSame = hasSame;
            }

            public double getTkCommFee() {
                return tkCommFee;
            }

            public void setTkCommFee(double tkCommFee) {
                this.tkCommFee = tkCommFee;
            }

            public String getCouponLink() {
                return couponLink;
            }

            public void setCouponLink(String couponLink) {
                this.couponLink = couponLink;
            }

            public String getCouponLinkTaoToken() {
                return couponLinkTaoToken;
            }

            public void setCouponLinkTaoToken(String couponLinkTaoToken) {
                this.couponLinkTaoToken = couponLinkTaoToken;
            }

            public int getDayLeft() {
                return dayLeft;
            }

            public void setDayLeft(int dayLeft) {
                this.dayLeft = dayLeft;
            }

            public int getIncludeDxjh() {
                return includeDxjh;
            }

            public void setIncludeDxjh(int includeDxjh) {
                this.includeDxjh = includeDxjh;
            }

            public String getAuctionTag() {
                return auctionTag;
            }

            public void setAuctionTag(String auctionTag) {
                this.auctionTag = auctionTag;
            }

            public int getCouponStartFee() {
                return couponStartFee;
            }

            public void setCouponStartFee(int couponStartFee) {
                this.couponStartFee = couponStartFee;
            }

            public String getCouponEffectiveStartTime() {
                return couponEffectiveStartTime;
            }

            public void setCouponEffectiveStartTime(String couponEffectiveStartTime) {
                this.couponEffectiveStartTime = couponEffectiveStartTime;
            }

            public String getCouponEffectiveEndTime() {
                return couponEffectiveEndTime;
            }

            public void setCouponEffectiveEndTime(String couponEffectiveEndTime) {
                this.couponEffectiveEndTime = couponEffectiveEndTime;
            }

            public Object getHasUmpBonus() {
                return hasUmpBonus;
            }

            public void setHasUmpBonus(Object hasUmpBonus) {
                this.hasUmpBonus = hasUmpBonus;
            }

            public Object getUmpBonus() {
                return umpBonus;
            }

            public void setUmpBonus(Object umpBonus) {
                this.umpBonus = umpBonus;
            }

            public Object getIsBizActivity() {
                return isBizActivity;
            }

            public void setIsBizActivity(Object isBizActivity) {
                this.isBizActivity = isBizActivity;
            }

            public Object getCouponShortLink() {
                return couponShortLink;
            }

            public void setCouponShortLink(Object couponShortLink) {
                this.couponShortLink = couponShortLink;
            }

            public String getCouponInfo() {
                return couponInfo;
            }

            public void setCouponInfo(String couponInfo) {
                this.couponInfo = couponInfo;
            }

            public Object getEventRate() {
                return eventRate;
            }

            public void setEventRate(Object eventRate) {
                this.eventRate = eventRate;
            }

            public Object getRootCategoryName() {
                return rootCategoryName;
            }

            public void setRootCategoryName(Object rootCategoryName) {
                this.rootCategoryName = rootCategoryName;
            }

            public Object getCouponOriLink() {
                return couponOriLink;
            }

            public void setCouponOriLink(Object couponOriLink) {
                this.couponOriLink = couponOriLink;
            }

            public Object getUserTypeName() {
                return userTypeName;
            }

            public void setUserTypeName(Object userTypeName) {
                this.userTypeName = userTypeName;
            }

//            public static class TkSpecialCampaignIdRateMapBean {
//                /**
//                 * 4074438 : 4.50
//                 */
//
//                @com.google.gson.annotations.SerializedName("4074438")
//                private String _$4074438;
//
//                public String get_$4074438() {
//                    return _$4074438;
//                }
//
//                public void set_$4074438(String _$4074438) {
//                    this._$4074438 = _$4074438;
//                }
//            }
        }

        public static class NavigatorBean {
            /**
             * name : 零食/坚果/特产
             * id : 50002766
             * type : category
             * level : 1
             * count : 656854
             * flag : qp_commend
             * subIds : [{"name":"膨化食品","id":50023546,"type":"category","level":1,"count":"68841","flag":"qp_commend","subIds":null},{"name":"糖果","id":50034038,"type":"category","level":1,"count":"59731","flag":"qp_commend","subIds":null},{"name":"豆腐干","id":56038004,"type":"category","level":1,"count":"26006","flag":"qp_commend","subIds":null},{"name":"鸭肉类零食","id":50008618,"type":"category","level":1,"count":"24290","flag":"qp_commend","subIds":null},{"name":"鱼系列","id":50009558,"type":"category","level":1,"count":"23337","flag":"qp_commend","subIds":null},{"name":"鸡肉类零食","id":50008617,"type":"category","level":1,"count":"17426","flag":"qp_commend","subIds":null},{"name":"素肉","id":56014006,"type":"category","level":1,"count":"12959","flag":"qp_commend","subIds":null},{"name":"夹心饼干","id":56432006,"type":"category","level":1,"count":"12729","flag":"qp_commend","subIds":null},{"name":"曲奇饼干","id":56414006,"type":"category","level":1,"count":"11489","flag":"qp_commend","subIds":null},{"name":"鱿鱼系列","id":50009557,"type":"category","level":1,"count":"8142","flag":"qp_commend","subIds":null},{"name":"蛋卷饼干","id":56442004,"type":"category","level":1,"count":"4035","flag":"qp_commend","subIds":null},{"name":"营养（消化）饼干","id":56460005,"type":"category","level":1,"count":"2519","flag":"qp_commend","subIds":null},{"name":"黑巧克力","id":56444008,"type":"category","level":1,"count":"2319","flag":"qp_commend","subIds":null},{"name":"果仁巧克力","id":56438012,"type":"category","level":1,"count":"862","flag":"qp_commend","subIds":null}]
             */

            private String name;
            private int id;
            private String type;
            private int level;
            private String count;
            private String flag;
            private List<SubIdsBean> subIds;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public List<SubIdsBean> getSubIds() {
                return subIds;
            }

            public void setSubIds(List<SubIdsBean> subIds) {
                this.subIds = subIds;
            }

            public static class SubIdsBean {
                /**
                 * name : 膨化食品
                 * id : 50023546
                 * type : category
                 * level : 1
                 * count : 68841
                 * flag : qp_commend
                 * subIds : null
                 */

                private String name;
                private int id;
                private String type;
                private int level;
                private String count;
                private String flag;
                private Object subIds;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getFlag() {
                    return flag;
                }

                public void setFlag(String flag) {
                    this.flag = flag;
                }

                public Object getSubIds() {
                    return subIds;
                }

                public void setSubIds(Object subIds) {
                    this.subIds = subIds;
                }
            }
        }
    }

    public static class InfoBean {
        /**
         * message : null
         * pvid : 10_113.246.186.18_903_1510151381496
         * ok : true
         */

        private Object message;
        private String pvid;
        private boolean ok;

        public Object getMessage() {
            return message;
        }

        public void setMessage(Object message) {
            this.message = message;
        }

        public String getPvid() {
            return pvid;
        }

        public void setPvid(String pvid) {
            this.pvid = pvid;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }
    }
}
