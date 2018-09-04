package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.domain.Ads;
import com.ogc.standard.dto.req.XN625220Req;
import com.ogc.standard.dto.req.XN625221Req;

/**
 * @author: taojian 
 * @since: 2018年9月4日 下午3:58:00 
 * @history:
 */
public interface IAdsAO {

    public void publishAds(XN625220Req req);

    public void editAds(XN625221Req req);

    // 谁查的详情就是谁的 userId
    public Object adsDetail(String adsCode, String searchUserUserId);

    // public void draftPublish(XN625220Req req);

    public void xiaJiaAds(String adsCode, String userId);

    public void checkXiajia(String adsCode);

    public Object frontPage(Integer start, Integer limit, Ads condition);

    public Object ossPage(Integer start, Integer limit, Ads condition);

    public List<Ads> frontSearchAdsByNickName(String nickName);

}
