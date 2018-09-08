package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * 盘口档位
 * @author: lei 
 * @since: 2018年8月29日 下午4:03:55 
 * @history:
 */
public class HandicapGrade {

    // 价格
    private BigDecimal price;

    private List<Handicap> handicapList;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<Handicap> getHandicapList() {
        return handicapList;
    }

    public void setHandicapList(List<Handicap> handicapList) {
        this.handicapList = handicapList;
    }

}
