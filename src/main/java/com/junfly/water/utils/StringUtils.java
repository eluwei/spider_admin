package com.junfly.water.utils;

/**
 * @author: pq
 * @Description:
 * @Date: 2017/11/5 14:59
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            if (hex[i].length() == 4) {
                int data = Integer.parseInt(hex[i], 16);
                // 追加成string
                string.append((char) data);
            } else {
                int data = Integer.parseInt(hex[i].substring(0, 4), 16);
                // 追加成string
                string.append((char) data).append(hex[i].substring(4));
            }
        }
        return string.toString();
    }
}
