package com.ogc.standard.dto.res;

import java.util.List;

import com.ogc.standard.domain.HandicapItem;

public class XN650065Res {

    // 买方深度
    private List<HandicapItem> bids;

    // 卖方深度
    private List<HandicapItem> asks;

    public XN650065Res() {
    }

    public XN650065Res(List<HandicapItem> bids, List<HandicapItem> asks) {
        this.bids = bids;
        this.asks = asks;
    }

    public List<HandicapItem> getBids() {
        return bids;
    }

    public void setBids(List<HandicapItem> bids) {
        this.bids = bids;
    }

    public List<HandicapItem> getAsks() {
        return asks;
    }

    public void setAsks(List<HandicapItem> asks) {
        this.asks = asks;
    }

}
