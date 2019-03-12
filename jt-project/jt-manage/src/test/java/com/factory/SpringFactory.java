package com.factory;

import org.springframework.beans.factory.FactoryBean;

import java.util.Calendar;

/**
 * @author chao
 * @date 2019/2/18 - 12:21
 */
public class SpringFactory implements FactoryBean<Calendar> {

    @Override
    public Calendar getObject() throws Exception {
        return Calendar.getInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return Calendar.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
