package com.ogc.standard.ao;

import java.util.List;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.Coin;
import com.cdkj.coin.wallet.dto.req.XN802250Req;
import com.cdkj.coin.wallet.dto.req.XN802252Req;
import com.cdkj.coin.wallet.dto.req.XN802270Req;
import com.cdkj.coin.wallet.dto.res.XN802270Res;

public interface ICoinAO {
    static final String DEFAULT_ORDER_COLUMN = "order_no";

    // 新增token币种
    public void addCoinAndPublish(XN802250Req req);

    // 编辑币种基本信息
    public int editCoin(XN802252Req req);

    // 发布币种
    public void publish(String symbol, String updater, String remark);

    // 撤下币种
    public void revoke(String symbol, String updater, String remark);

    // 分页查
    public Paginable<Coin> queryCoinPage(int start, int limit, Coin condition);

    // 列表查
    public List<Coin> queryCoinList(Coin condition);

    // 详情查
    public Coin getCoin(String symbol);

    // 查询币种余额
    public XN802270Res queryCoinBalance(XN802270Req req);

}
