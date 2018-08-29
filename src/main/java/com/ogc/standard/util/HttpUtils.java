package com.ogc.standard.util;

import java.net.InetSocketAddress;
import java.net.Proxy;

import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:31:21 
 * @history:
 */
public class HttpUtils {

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

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .proxy(new Proxy(Proxy.Type.SOCKS,
                new InetSocketAddress(proxyHost, proxyPort)))
            .build();

        // OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

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
