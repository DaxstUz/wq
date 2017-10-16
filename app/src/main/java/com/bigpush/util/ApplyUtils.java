package com.bigpush.util;

import android.content.Context;

import java.io.UnsupportedEncodingException;

/**
 * Created by liyuanjing on 2015/10/25.
 */
public class ApplyUtils {
    public static boolean isChineseChar(char c) throws UnsupportedEncodingException {          // 如果字节数大于1，是汉字
        return String.valueOf(c).getBytes("UTF-8").length > 1;
    }

    public static String substring(String orignal, int count)
            throws UnsupportedEncodingException {
        // 原始字符不为null，也不是空字符串
        if (orignal != null && !"".equals(orignal)) {
            // 将原始字符串转换为UTF-8编码格式
            orignal = new String(orignal.getBytes(), "UTF-8");//
            // System.out.println(orignal);
            //System.out.println(orignal.getBytes().length);
            // 要截取的字节数大于0，且小于原始字符串的字节数
            if (count > 0 && count < orignal.getBytes("UTF-8").length) {
                StringBuffer buff = new StringBuffer();
                char c;
                for (int i = 0; i < count; i++) {
                    c = orignal.charAt(i);
                    buff.append(c);
                    if (ApplyUtils.isChineseChar(c)) {
                        // 遇到中文汉字，截取字节总数减2
                        --count;
                        --count;
                    }
                }
                return new String(buff.toString().getBytes(),"UTF-8");
            }
        }
        return orignal;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
