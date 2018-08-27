package com.ogc.standard.ao;

import java.util.List;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.token.TokenTransaction;

public interface ITokenTransactionAO {

    // 充值
    public String chargeNotice(TokenTransaction tokenTransaction);

    // 提现
    public void withdrawNotice(TokenTransaction tokenTransaction);

    public void depositNotice(TokenTransaction tokenTransaction);

    public Paginable<TokenTransaction> queryTokenTransactionPage(int start,
            int limit, TokenTransaction condition);

    public List<TokenTransaction> queryTokenTransactionList(
            TokenTransaction condition);

    public TokenTransaction getTokenTransaction(Long id);

    // 只落地交易记录，不做其他处理
    public void addTokenTransaction(TokenTransaction tokenTransaction);

    // 增加空投金额
    public void addKongtouNotice(TokenTransaction tokenTransaction);

}
