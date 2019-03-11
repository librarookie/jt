package com.demo;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @author chao
 * @date 2019/2/18 - 16:01
 */
public class TestHttpClient {

    @Test
    public void get() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://www.bilibili.com/";
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("欢迎来到二次元 ~");
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("result = " + result);
        }
    }
}
