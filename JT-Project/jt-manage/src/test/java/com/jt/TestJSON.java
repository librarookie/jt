package com.jt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chao
 * @date 2019/2/14 - 14:35
 */
public class TestJSON {

    @Test
    public void objectToJSON() throws IOException {
        User user = new User();
        user.setId(100);
        user.setName("伍六七");
        user.setAge(18);
        user.setSex("男");

        ObjectMapper mapper = new ObjectMapper();
        // 将对象转化为 JSON 串
        String json = mapper.writeValueAsString(user);
        System.out.println("json = " + json);
        /**
         * 将json转化为对象
         * src 表述需要转化的json 数据
         * valueType 表述转化的数据类型
         */
        User tempUser = mapper.readValue(json, User.class);
        System.out.println("tempUser = " + tempUser);
    }
    @Test
    public void listToJSON() throws IOException {
        User user = new User();
        user.setId(100);
        user.setName("大宝");
        user.setAge(18);
        user.setSex("男");
        User user2 = new User();
        user2.setId(100);
        user2.setName("大宝");
        user2.setAge(18);
        user2.setSex("男");


        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);

        // 将对象转化为json串
        ObjectMapper mapper = new ObjectMapper();
        String listJSON = mapper.writeValueAsString(userList);
        System.out.println("listJSON = " + listJSON);

        // 将listJSON 串转化为对象List<User>
        /*@SuppressWarnings("unchecked")  // 压制警告
        List<User> list = mapper.readValue(listJSON, userList.getClass());*/
        User[] users = mapper.readValue(listJSON, User[].class);
        System.out.println(Arrays.asList(users));

    }
}
