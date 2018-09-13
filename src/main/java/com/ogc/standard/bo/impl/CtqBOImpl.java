/**
 * @Title CtqBOImpl.java 
 * @Package com.cdkj.coin.bo.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午1:56:29 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICtqBO;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.domain.CtqBtcUtxo;
import com.ogc.standard.domain.SYSConfig;
import com.ogc.standard.dto.req.XN616000Req;
import com.ogc.standard.dto.req.XN616020Req;
import com.ogc.standard.dto.req.XN625917Req;
import com.ogc.standard.dto.req.XN626040Req;
import com.ogc.standard.dto.req.XN626060Req;
import com.ogc.standard.dto.req.XN626080Req;
import com.ogc.standard.dto.req.XN626100Req;
import com.ogc.standard.dto.req.XN626120Req;
import com.ogc.standard.dto.req.XN626140Req;
import com.ogc.standard.dto.req.XN626160Req;
import com.ogc.standard.enums.EOriginialCoin;
import com.ogc.standard.enums.ESystemCode;
import com.ogc.standard.http.BizConnecter;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午1:56:29 
 * @history:
 */
@Component
public class CtqBOImpl implements ICtqBO {

    /** 
     * @see com.cdkj.coin.wallet.bo.ICtqBO#uploadEthAddress(java.lang.String, java.lang.String)
     */
    @Override
    public void uploadEthAddress(String address, String type) {
        XN616000Req req = new XN616000Req();
        req.setAddress(address);
        req.setType(type);
        BizConnecter.getBizData("626000", JsonUtil.Object2Json(req));
    }

    @Override
    public void confirmEth(List<String> hashList) {
        XN616020Req req = new XN616020Req();
        req.setHashList(hashList);
        BizConnecter.getBizData("626020", JsonUtil.Object2Json(req));
    }

    @Override
    public BigInteger getScanedBlockNumber(EOriginialCoin coin) {
        BigInteger number = BigInteger.ZERO;
        XN625917Req req = new XN625917Req();
        if (EOriginialCoin.ETH == coin) {
            req.setKey("curEthBlockNumber");
        } else if (EOriginialCoin.BTC == coin) {
            req.setKey("curBtcBlockNumber");
        }
        req.setSystemCode(ESystemCode.COIN.getCode());
        req.setCompanyCode(ESystemCode.COIN.getCode());
        SYSConfig sysConfig = BizConnecter.getBizData("625917",
            JsonUtil.Object2Json(req), SYSConfig.class);
        if (sysConfig != null) {
            number = new BigInteger(sysConfig.getCvalue())
                .subtract(BigInteger.ONE);
        }
        return number;
    }

    @Override
    public void uploadScAddress(String address, String type) {
        XN626040Req req = new XN626040Req();
        req.setAddress(address);
        req.setType(type);
        BizConnecter.getBizData("626040", JsonUtil.Object2Json(req));
    }

    @Override
    public void confirmSc(List<String> hashList) {
        XN626060Req req = new XN626060Req();
        req.setHashList(hashList);
        BizConnecter.getBizData("626060", JsonUtil.Object2Json(req));
    }

    @Override
    public void uploadBtcAddress(String address) {
        XN626080Req req = new XN626080Req();
        req.setAddress(address);
        BizConnecter.getBizData("626080", JsonUtil.Object2Json(req));
    }

    @Override
    public void confirmBTC(List<CtqBtcUtxo> utxoList) {
        XN626100Req req = new XN626100Req();
        req.setUtxoList(utxoList);
        BizConnecter.getBizData("626100", JsonUtil.Object2Json(req));
    }

    @Override
    public void uploadContractAddress(String symbol, String contractAddress) {
        XN626120Req req = new XN626120Req();
        req.setSymbol(symbol);
        req.setContractAddress(contractAddress);
        BizConnecter.getBizData("626120", JsonUtil.Object2Json(req));
    }

    @Override
    public void uploadTokenAddress(String address, String symbol) {
        XN626140Req req = new XN626140Req();
        req.setSymbol(symbol);
        req.setAddress(address);
        BizConnecter.getBizData("626140", JsonUtil.Object2Json(req));
    }

    @Override
    public void confirmToken(List<Long> idList) {
        XN626160Req req = new XN626160Req();
        req.setIdList(idList);
        BizConnecter.getBizData("626160", JsonUtil.Object2Json(req));

    }
}
