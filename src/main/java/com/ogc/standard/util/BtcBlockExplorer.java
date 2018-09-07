package com.ogc.standard.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ogc.standard.bitcoin.original.BTCFee;
import com.ogc.standard.bitcoin.original.BTCOriginalTx;
import com.ogc.standard.bitcoin.original.BTCTXs;
import com.ogc.standard.common.PropertiesUtil;
import com.ogc.standard.exception.BizException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class BtcBlockExplorer {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    private Integer maxFeePerByteCanAccept = 200;// 平台可接受最大交易手续费

    @Nullable
    public BTCOriginalTx queryTxHash(String txid) {

        try {

            String txURL = this.baseApiURL() + "/tx/" + txid;
            String jsonStr = this.get(txURL);
            if (jsonStr == null) {
                return null;
            }
            return com.alibaba.fastjson.JSON.parseObject(jsonStr,
                BTCOriginalTx.class);

        } catch (Exception e) {

            throw new BizException("获取区块数据失败");

        }

    }

    public Long getBlockCount() {
        try {

            String txURL = this.baseApiURL() + "/status?q=getBlockCount";
            String jsonStr = this.get(txURL);
            if (jsonStr == null) {
                return null;
            }
            return com.alibaba.fastjson.JSON.parseObject(jsonStr)
                .getLong("blockcount");

        } catch (Exception e) {

            throw new BizException("获取区块数据失败");

        }
    }

    public Integer getFee() {

        String jsonStr = this.get(this.feeUrl());
        if (jsonStr == null) {
            throw new BizException("获取区块数据失败");
        }

        try {

            BTCFee fee = JSON.parseObject(jsonStr, BTCFee.class);
            // 应该读取一个配置值，获取手续费如果大于这个值，就取这个值
            Integer maxFeePerByte = maxFeePerByteCanAccept;
            Integer fastFee = fee.getHalfHourFee();

            return fastFee > maxFeePerByte ? maxFeePerByte : fastFee;

        } catch (Exception e) {

            throw new BizException("拉取手续费失败");

        }

    }

    /**
     * 返回
     *
     * @param blockHeight
     * @param pageNum
     * @return
     */
    public BTCTXs getBlockTxs(Long blockHeight, Integer pageNum) {

        String jsonStr = this.get(this.blockTxsUrl(blockHeight, pageNum));
        BTCTXs btctXs = JSON.parseObject(jsonStr, BTCTXs.class);
        return btctXs;

    }

    private String blockTxsUrl(Long blockHeight, Integer pageNum) {

        String blockHassh = this.blockHash(blockHeight);
        return this.baseApiURL() + "/txs/?block=" + blockHassh + "&pageNum="
                + pageNum;

    }

    private String blockHash(Long blockHeight) {

        String requestUrl = this.baseApiURL() + "/block-index/" + blockHeight;
        String jsonStr = this.get(requestUrl);
        Map map = JSONObject.parseObject(jsonStr, Map.class);
        return (String) map.get("blockHash");

    }

    private String broadcastRawTxUrl() {

        return this.baseApiURL() + "/tx/send";

    }

    public Integer getMaxFeePerByteCanAccept() {
        return maxFeePerByteCanAccept;
    }

    /**
     * 成功返回 txid, 失败返回null
     *
     * @param rawTx
     * @return
     */
    @Nullable
    public String broadcastRawTx(String rawTx) {

        try {

            // 2.进行广播
            FormBody formBody = new FormBody.Builder().add("rawtx", rawTx)
                .build();
            Request request = new Request.Builder().post(formBody)
                .url(this.broadcastRawTxUrl()).build();
            //
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            if (response.code() == 200) {

                String jsonStr = response.body().string();
                Map map = JSON.parseObject(jsonStr, HashMap.class);
                String trueTxid = (String) map.get("txid");
                if (trueTxid == null && trueTxid.length() <= 0) {

                    return null;

                } else {

                    return trueTxid;
                }

            } else {

                return null;

            }

        } catch (Exception e) {

            return null;
        }

    }

    //
    private String feeUrl() {
        // {"fastestFee":250,"halfHourFee":240,"hourFee":130}
        return PropertiesUtil.Config.BTC_FEE;

    }

    private String get(String url) throws BizException {

        // 200 ok
        // 404 如果没有
        Request req = new Request.Builder().get().url(url).build();
        try {

            Call call = okHttpClient.newCall(req);
            Response response = call.execute();

            if (response.code() == 200) {

                return response.body().string();

            } else if (response.code() == 404) {

                return null;

            } else {

                throw new BizException("获取区块数据失败");

            }

        } catch (Exception e) {

            throw new BizException("获取区块数据失败");

        }

    }

    public boolean verifyAddress(String address) {
        try {

            String txURL = this.baseApiURL() + "/addr-validate/" + address;
            String jsonStr = this.get(txURL);
            if (jsonStr == null) {
                return false;
            }
            return new Boolean(jsonStr);

        } catch (Exception e) {

            throw new BizException("获取区块数据失败");

        }
    }

    private String baseApiURL() {
        return PropertiesUtil.Config.BTC_URL;
    }

}
