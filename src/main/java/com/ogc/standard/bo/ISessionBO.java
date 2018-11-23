/**
 * @Title ISessionBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午1:18:46 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Session;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午1:18:46 
 * @history:
 */
public interface ISessionBO extends IPaginableBO<Session> {

    public Session saveSession(String type, String user1, String user2);

    public void refreshUnreadSum1(Session data, Long sum);

    public void refreshUnreadSum2(Session data, Long sum);

    public Session getSessionExist(String type, String user1, String user2);

    public List<Session> queryUnread(String user1, String user2);

    public List<Session> querySessionList(Session condition);

    public Session getSession(String code);
}
