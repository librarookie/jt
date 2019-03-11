package com.factory;

import java.util.Calendar;

/**
 * @author chao
 * @date 2019/2/18 - 11:32
 */
public class InstanceFactory {

    // 测试实例化工厂模式
    public Calendar getInit() {
        return Calendar.getInstance();
    }
}
