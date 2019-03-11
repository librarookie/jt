package com.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

/**
 * @author chao
 * @date 2019/2/18 - 11:01
 */
public class TestFactory {
    @Test
    public void testCalender() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/factory.xml");
        Calendar calender1 = context.getBean("calender1", Calendar.class);
        Calendar calender2 = context.getBean("calender2", Calendar.class);
        Calendar calender3 = context.getBean("calender3", Calendar.class);
        System.out.println("calender1 = " + calender1.getTime());
        System.out.println("calender2 = " + calender2.getTime());
        System.out.println("calender3 = " + calender3.getTime());
    }
}
