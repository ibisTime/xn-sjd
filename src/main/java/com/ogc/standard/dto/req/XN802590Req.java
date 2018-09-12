/**
 * @Title XN625100Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月9日 下午7:02:19 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * 分页查询BTC交易记录
 * @author: taojian 
 * @since: 2018年9月11日 下午2:01:47 
 * @history:
 */
public class XN802590Req extends APageReq {

    private static final long serialVersionUID = -2919414958783911395L;

    // 交易哈希
    private String txid;

    // 区块哈希
    private String blockHash;

    // 区块高度
    private String blockHeight;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(String blockHeight) {
        this.blockHeight = blockHeight;
    }

}
