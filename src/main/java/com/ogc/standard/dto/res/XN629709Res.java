package com.ogc.standard.dto.res;

import java.util.List;

/**
 * 列表查商品发货地/产地
 * @author: silver 
 * @since: Jan 16, 2019 5:51:47 PM 
 * @history:
 */
public class XN629709Res {

    private List<String> placeList;

    public List<String> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<String> placeList) {
        this.placeList = placeList;
    }

    public XN629709Res(List<String> placeList) {
        super();
        this.placeList = placeList;
    }

}
