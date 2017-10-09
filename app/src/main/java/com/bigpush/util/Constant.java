package com.bigpush.util;

import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;

public class Constant {

    public static int NET_WHAT=0;

//    public static final String loginUrl="http://www.baidu.com";

    public static AlibcShowParams alibcShowParams= new AlibcShowParams(OpenType.Auto, false);;//页面打开方式，默认，H5，Native

    private static String   HOSTSERVER="http://47.95.220.122/6api";

    public static final String deviceReg=HOSTSERVER+"/user/reg";//注册硬件设备
    public static final String userInit=HOSTSERVER+"/user/init";//游客账号分配
    public static final String homeType=HOSTSERVER+"/home/type";//商品分类信息获取



}
