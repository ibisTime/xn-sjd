/**
 * @Title HttpUtils.java 
 * @Package com.cdkj.info.util 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年4月11日 下午1:19:05 
 * @version V1.0   
 */
package com.ogc.standard.common;

import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/** 
 * @author: haiqingzheng 
 * @since: 2018年4月11日 下午1:19:05 
 * @history:
 */
public class OkHttpUtils {

    public static final MediaType JSON = MediaType
        .parse("application/json; charset=utf-8");

    public static int proxyPort = 1080;

    public static String proxyHost = "127.0.0.1";

    public static String doAccessHTTPPostJson(String sendUrl,
            String sendParam) {

        // OkHttpClient okHttpClient = new OkHttpClient.Builder()
        // .proxy(new Proxy(Proxy.Type.HTTP,
        // new InetSocketAddress(proxyHost, proxyPort)))
        // .build();

        OkHttpClient okHttpClient = new OkHttpClient();

        String requestStr = sendUrl;

        RequestBody requestBody = RequestBody.create(JSON, sendParam);

        Request request = new Request.Builder().post(requestBody)
            .url(requestStr).build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            String jsonStr = response.body().string();
            return jsonStr;
        } catch (Exception e) {
            throw new BizException("xn0000", "网络请求异常，原因" + e.getMessage());
        }
    }

    public static String doAccessHTTPGetJson(String sendUrl) {

        String requestStr = sendUrl;

        // OkHttpClient okHttpClient = new OkHttpClient.Builder()
        // .proxy(new Proxy(Proxy.Type.SOCKS,
        // new InetSocketAddress(proxyHost, proxyPort)))
        // .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().get().url(requestStr)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .addHeader("Language", "zh-cn").build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            String jsonStr = response.body().string();
            return jsonStr;
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "网络请求异常，原因" + e.getMessage());
        }
    }

}
