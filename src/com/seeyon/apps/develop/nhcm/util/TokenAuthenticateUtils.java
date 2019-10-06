package com.seeyon.apps.develop.nhcm.util;


import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;

/**
 * 获取调用RESTful接口调用的客户端
 *
 * @author by ybl
 * @Date 2019/8/8  10:11
 */
public class TokenAuthenticateUtils {

    public static CTPRestClient client;

    public static CTPRestClient getClient() {
        // 指定协议、IP和端口，获取ClientManager
        CTPServiceClientManager clientManager = CTPServiceClientManager.getInstance("http://localhost:80");
        // 取得REST动态客户机实例
        client = clientManager.getRestClient();
        client.authenticate("restName","123456");
        return client;



    }
}
