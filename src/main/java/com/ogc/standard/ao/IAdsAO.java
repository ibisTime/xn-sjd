package com.ogc.standard.ao;

import com.ogc.standard.domain.Ads;
import com.ogc.standard.dto.req.XN625220Req;

import java.util.List;

/**
 * Created by tianlei on 2017/十一月/14.
 */
public interface IAdsAO {

    public void publishAds(XN625220Req req);

    //谁查的详情就是谁的 userId
    public Object adsDetail(String adsCode, String searchUserUserId);

//    public void draftPublish(XN625220Req req);

    public void xiaJiaAds(String adsCode, String userId);

    public void checkXiajia(String adsCode);

    public Object frontPage(Integer start, Integer limit, Ads condition);

    public Object ossPage(Integer start, Integer limit, Ads condition);

    public List<Ads> frontSearchAdsByNickName(String nickName);

}
