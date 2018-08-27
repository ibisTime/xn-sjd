package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Coin;
import com.ogc.standard.dto.req.XN802000Req;
import com.ogc.standard.dto.req.XN802252Req;

public interface ICoinAO {
    static final String DEFAULT_ORDER_COLUMN = "order_no";

    // 新增token币种
    public void addCoinAndPublish(XN802000Req req);

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
    // public XN802270Res queryCoinBalance(XN802270Req req);

}
