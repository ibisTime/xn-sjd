/**
 * @Title ISessionMessageBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午1:26:07 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SessionMessage;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午1:26:07 
 * @history:
 */
public interface ISessionMessageBO extends IPaginableBO<SessionMessage> {

    public void saveMessage(String sessionCode, String userId, String content);

    public void refreshRead(SessionMessage data);

    public SessionMessage getMessage(Long id);

    public List<SessionMessage> querySessionMessages(String sessionCode,
            String userId, String status);
}
