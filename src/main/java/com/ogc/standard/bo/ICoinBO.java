package com.ogc.standard.bo;

import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.Coin;
import com.cdkj.coin.wallet.enums.ECoinStatus;

public interface ICoinBO extends IPaginableBO<Coin> {

    public boolean isSymbolExist(String symbol);

    public boolean isContractAddressExist(String type, String contractAddress);

    public String saveCoin(Coin data);

    public int refreshCoin(Coin data);

    public List<Coin> queryCoinList(Coin condition);

    public Coin getCoin(String symbol);

    public int refreshStatus(Coin coin, ECoinStatus status, String updater,
            String remark);

    public List<Coin> queryOpenCoinList();

    public void checkCoinCount(String type);

}
