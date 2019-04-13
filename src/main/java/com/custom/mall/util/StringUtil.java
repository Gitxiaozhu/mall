package com.custom.mall.util;


import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

    /**
     * 判断一个或多个是否为空
     *
     * @param objects
     * @return
     */
    public static boolean isBlank(Object... objects) {
        Boolean flag = false;
        for (Object object : objects) {
            if (null == object || "".equals(object.toString()) || "null".equals(object.toString())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 判断一个或多个是否不为空
     *
     * @param objects
     * @return
     */
    public static boolean isNotBlank(Object... objects) {
        return !isBlank(objects);
    }

    public static boolean isBlank(String str) {
        Object object = str;
        return isBlank(object);
    }

    public static boolean isNotBlank(String str) {
        Object object = str;
        return !isBlank(object);
    }
}
