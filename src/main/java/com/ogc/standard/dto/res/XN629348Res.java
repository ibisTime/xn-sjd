package com.ogc.standard.dto.res;

/**
 * 用户是否点赞/收藏
 * @author: silver 
 * @since: Nov 23, 2018 5:14:00 PM 
 * @history:
 */
public class XN629348Res {
    private String isPointCollect;

    public String getIsPointCollect() {
        return isPointCollect;
    }

    public void setIsPointCollect(String isPointCollect) {
        this.isPointCollect = isPointCollect;
    }

    public XN629348Res(String isPointCollect) {
        super();
        this.isPointCollect = isPointCollect;
    }

}
