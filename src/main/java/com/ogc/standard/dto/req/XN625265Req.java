/**
 * @Title XN625265Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月14日 下午2:41:11 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月14日 下午2:41:11 
 * @history:
 */
public class XN625265Req extends APageReq {

    private static final long serialVersionUID = 3251378162029317013L;

    // 工单编号
    private String code;

    // 交易订单编号
    private String tradeOrderCode;

    //_原告
    private String yuangao;

    //_被告
    private String beigao;

    //_状态
    private String status;

    // 处理人
    private String updater;

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTradeOrderCode() {
        return tradeOrderCode;
    }

    public void setTradeOrderCode(String tradeOrderCode) {
        this.tradeOrderCode = tradeOrderCode;
    }

    public String getYuangao() {
        return yuangao;
    }

    public void setYuangao(String yuangao) {
        this.yuangao = yuangao;
    }

    public String getBeigao() {
        return beigao;
    }

    public void setBeigao(String beigao) {
        this.beigao = beigao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }


}
