package com.bigpush.util;

import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;

public class Constant {

    public static int NET_WHAT=0;

//    public static final String loginUrl="http://www.baidu.com";

    public static AlibcShowParams alibcShowParams= new AlibcShowParams(OpenType.Auto, false);//页面打开方式，默认，H5，Native

    public static String   WEBSERVER="http://47.95.202.75:8090/bigpush/";
    //    private static String   HOSTSERVER="https://47.95.220.122/6api";
    private static String   HOSTSERVER="https://api.sir66.com";


    public static final String deviceReg=HOSTSERVER+"/user/reg";//注册硬件设备
    public static final String userInit=HOSTSERVER+"/user/init";//游客账号分配
    public static final String homeType=HOSTSERVER+"/home/type";//商品分类信息获取
    public static final String commodityBaseCode=HOSTSERVER+"/commodityBase/code";//通过Code获取商品详情
    public static final String commodityBaseRecommend=HOSTSERVER+"/commodityBase/recommend";//推荐商品
    public static final String overallGetHotText=HOSTSERVER+"/overall/getHotText";//获取热门搜索词
    public static final String overallGetResult=HOSTSERVER+"/overall/getResult";//获取搜索结果
    public static final String overallToSendMsg=HOSTSERVER+"/overall/toSendMsg";//意见提交
    public static final String ninePointNineCommodityList=HOSTSERVER+"/ninePointNine/commodityList";//9.9获取商品列表



}
