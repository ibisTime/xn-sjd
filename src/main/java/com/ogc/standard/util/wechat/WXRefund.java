package com.ogc.standard.util.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;

import com.ogc.standard.common.PropertiesUtil;

/**
 * 微信退款
 * @author: silver 
 * @since: Dec 17, 2018 2:56:28 PM 
 * @history:
 */
public class WXRefund {

    private static String WECHAT_REFUND_URL = PropertiesUtil.Config.WECHAT_REFUND_URL;

    private String appid;

    private String mch_id;

    private String nonce_str = OrderUtil.CreateNoncestr();

    private String out_refund_no;

    private String out_trade_no;

    private String refund_fee;

    private String total_fee;

    private String sign;

    private String privateKey;

    /**
     * 退款
     * @return 
     * @create: Dec 17, 2018 3:24:14 PM silver
     * @history:
     */
    public String submitXmlRefund() {
        String return_code = null;

        InputStream instream = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream("apiclient_cert.p12");
        KeyStore keyStore = null;

        try {
            keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(instream, mch_id.toCharArray());

            SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, mch_id.toCharArray()).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).build();

            HttpPost httpPost = new HttpPost(WECHAT_REFUND_URL);
            String xml = getPackage();
            StringEntity entity;

            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpclient.execute(httpPost);

            HttpEntity resEntity = response.getEntity();
            String result = EntityUtils.toString(resEntity, "UTF-8");
            // System.out.println(result);

            result = result.replaceAll("<![CDATA[|]]>", "");
            return_code = Jsoup.parse(result).select("return_code").html();

            if ("SUCCESS".equals(return_code)) {
                System.out.println("微信退款成功");

                // TODO
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return return_code;
    }

    public String getPackage() {
        SortedMap<Object, Object> treeMap = new TreeMap<Object, Object>();
        treeMap.put("appid", this.appid);
        treeMap.put("mch_id", this.mch_id);
        treeMap.put("nonce_str", this.nonce_str);
        treeMap.put("out_refund_no", this.out_refund_no);
        treeMap.put("out_trade_no", this.out_trade_no);
        treeMap.put("refund_fee", this.refund_fee);
        treeMap.put("total_fee", this.total_fee);

        sign = createSign("utf-8", privateKey, treeMap);
        treeMap.put("sign", sign);

        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (Map.Entry<Object, Object> entry : treeMap.entrySet()) {
            xml.append("<" + entry.getKey() + ">").append(entry.getValue())
                .append("</" + entry.getKey() + ">\n");
        }
        xml.append("</xml>");
        System.out.println(xml.toString());
        return xml.toString();
    }

    public String createSign(String characterEncoding, String secretKey,
            SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it
                .next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + secretKey);
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding)
            .toUpperCase();
        return sign;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

}
