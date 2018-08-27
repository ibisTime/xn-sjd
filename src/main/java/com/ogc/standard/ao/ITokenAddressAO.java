/**
 * @Title ITokenAddressAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:38:33 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.dto.res.XN802906Res;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.token.TokenAddress;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:38:33 
 * @history:
 */
public interface ITokenAddressAO {

    // 新增用户地址
    public void addTokenAddress(String address, String label, String userId,
            String smsCaptcha, String isCerti, String tradePwd,
            String googleCaptcha, String symbol);

    // 弃用地址
    public void abandonAddress(String code);

    // 根据地址获取地址类型
    public EAddressType getType(String address, String symbol);

    // 生成散取️地址（有私钥）
    public String generateMAddress(String symbol);

    // 生成空投️地址（有私钥）
    public String generateHAddress(String symbol);

    // 导入归集地址（无私钥）
    public String importWAddress(String address, String symbol);

    public Paginable<TokenAddress> queryTokenAddressPage(int start, int limit,
            TokenAddress condition);

    public TokenAddress getTokenAddress(String code);

    public BigDecimal getTotalBalance(EAddressType type, String symbol);

    // 转账
    public String transfer(String code, String toAddress, BigDecimal value);

    // 空投情况
    public XN802906Res getKongtouInfo(String symbol);

}
