package com.ogc.standard.market;

import java.util.List;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午8:10:23 
 * @history:
 */
public class MarketDepth {

    // 买方深度
    private List<MarketDepthItem> bids;

    // 卖方深度
    private List<MarketDepthItem> asks;

    public List<MarketDepthItem> getBids() {
        return bids;
    }

    public void setBids(List<MarketDepthItem> bids) {
        this.bids = bids;
    }

    public List<MarketDepthItem> getAsks() {
        return asks;
    }

    public void setAsks(List<MarketDepthItem> asks) {
        this.asks = asks;
    }

}
