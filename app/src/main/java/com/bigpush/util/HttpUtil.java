package com.bigpush.util;

public class HttpUtil {

    public static String getUrl(String url){
        if(url==null){
           return "";
        }
        if(url.startsWith("http")){
            return url;
        }else{
            return "http:"+url;
        }
    }
}
