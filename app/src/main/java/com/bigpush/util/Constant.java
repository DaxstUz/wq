package com.bigpush.util;

import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;

public class Constant {

    public static boolean clean=false;

    public static int NET_WHAT=0;

//    public static final String loginUrl="http://www.baidu.com";

    public static AlibcShowParams alibcShowParams= new AlibcShowParams(OpenType.Auto, false);//页面打开方式，默认，H5，Native

    public static String   WEBSERVER="https://api.sir66.com/6web/";
    private static String   HOSTSERVER="https://api.sir66.com";
//    private static String   HOSTSERVER="http://47.95.220.122";


    public static final String deviceReg=HOSTSERVER+"/user/reg";//注册硬件设备
    public static final String userBind=HOSTSERVER+"/user/bind";//第三方绑定
    public static final String userget=HOSTSERVER+"/user/get";//获取用户数据
    public static final String commissionSendApply=HOSTSERVER+"/commission/sendApply";//合伙人申请提交
    public static final String commissionGetApplyList=HOSTSERVER+"/commission/getApplyList";//合伙人申请提交
    public static final String commissionPassApply=HOSTSERVER+"/commission/passApply";//申请通过接口
    public static final String userInit=HOSTSERVER+"/user/init";//游客账号分配
    public static final String homeType=HOSTSERVER+"/home/type";//商品分类信息获取
    public static final String homeCarousel=HOSTSERVER+"/home/carousel";//	获取首页轮播信息接口
    public static final String homeCommodityTypeList=HOSTSERVER+"/home/commodityTypeList";//首页分类商品列表
    public static final String homeCommodityByItem=HOSTSERVER+"/home/commodityByItem";//通过栏目获取商品信息
    public static final String homeCommodityList=HOSTSERVER+"/home/commodityList";//首页商品列表
    public static final String homeDaySeckill=HOSTSERVER+"/home/daySeckill";//首页每日秒杀接口
    public static final String homeRecommendItem=HOSTSERVER+"/home/recommendItem";//首页获取推荐栏目接口
    public static final String homeUpdownInfo=HOSTSERVER+"/home/updownInfo";//首页上下翻动广告
    public static final String commodityBaseCode=HOSTSERVER+"/commodityBase/code";//通过Code获取商品详情
    public static final String commodityBaseRecommend=HOSTSERVER+"/commodityBase/recommend";//推荐商品
    public static final String overallGetHotText=HOSTSERVER+"/overall/getHotText";//获取热门搜索词
    public static final String overallGetResult=HOSTSERVER+"/overall/getResult";//获取搜索结果
    public static final String userMsg=HOSTSERVER+"/user/msg";//用户获取消息列表接口
    public static final String overallToSendMsg=HOSTSERVER+"/overall/toSendMsg";//意见提交
    public static final String ninePointNineCommodityList=HOSTSERVER+"/ninePointNine/commodityList";//9.9获取商品列表
    public static final String commodityBaseShareText=HOSTSERVER+"/commodityBase/shareText";//商品文案获取
    public static final String commodityBaseReceiveUrl=HOSTSERVER+"/commodityBase/receiveUrl";//领卷URL获取
    public static final String infoList=HOSTSERVER+"/info/list";//资讯页列表获取
    public static final String infoTypeItem=HOSTSERVER+"/info/typeItem";//资讯页栏目资讯列表获取
    public static final String infoBaseCode=HOSTSERVER+"/infoBase/code";//获取资讯详情
    public static final String infoBaseFabulous=HOSTSERVER+"/infoBase/fabulous";//资讯点赞
    public static final String infoRecommendItem=HOSTSERVER+"/info/recommendItem";//资讯页推荐栏目获取
    public static final String commodityBaseGetUrl=HOSTSERVER+"/commodityBase/getUrl";//超级搜商品转链


}
