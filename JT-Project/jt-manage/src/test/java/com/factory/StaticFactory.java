package com.factory;

import java.util.Calendar;

/**
 * @author chao
 * @date 2019/2/18 - 10:55
 */
public class StaticFactory {

    // 必须有静态方法
    public static Calendar getInit() {
        return Calendar.getInstance();
    }
}
