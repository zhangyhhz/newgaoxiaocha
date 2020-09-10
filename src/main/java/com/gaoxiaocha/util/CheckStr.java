package com.gaoxiaocha.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *判断一个
 *
 *@author zyh
 *@date 2020/5/27
 */
public class CheckStr {
    private static Pattern NUMBER_PATTERN = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");

    public static boolean checkStr(String str) {
        Matcher matcher = NUMBER_PATTERN.matcher(str);
        if (!matcher.matches()){
            return false;
        }else {
            return true;
        }
    }
}
