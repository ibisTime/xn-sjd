/**
 * @Title ISmsBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午2:04:01 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Sms;
import com.ogc.standard.domain.User;

/** 
 * @author: dl 
 * @since: 2018年8月22日 下午2:04:01 
 * @history:
 */
public interface ISmsBO extends IPaginableBO<Sms> {

    public boolean isSmsExit(String code);

    public String saveSms(Sms data);

    public int refreshSms(Sms data);

    public int revokeSms(String code, String updater, String remark);

    // 添加认养快报
    public void saveAdoptBulletin(String userId, String count, String sellType,
            String productName);

    // 添加集体快报
    public void saveFirstAdoptBulletin(String userId, String productName);

    // 添加集体过期快报
    public void saveExpireGroupAdoptBulletin(String userId, String productName);

    // 添加商城快报
    public void saveCommodityBulletin(String userId, String count);

    // 添加文章点赞消息
    public void saveArticlePoint(String userId, String articleTitle, User user);

    // 添加文章点赞消息
    public void saveAdoptOrderTreeExpire(String userId, String treeName,
            String treeNumber);

    // 阅读快报
    public void readBulletin(String code);

    public List<Sms> querySmsList(Sms data);

    public Sms getSms(String code);
}
