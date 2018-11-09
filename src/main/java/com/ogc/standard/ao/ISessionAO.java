/**
 * @Title ISessionAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午2:01:25 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Session;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午2:01:25 
 * @history:
 */
public interface ISessionAO {

    public void send(String user1, String user2, String content, String type);

    public void replay(String code, String userId, String content);

    public void read(String code, String userId);

    public Paginable<Session> querySessionPage(int start, int limit,
            Session condition);

    public List<Session> querySessionList(Session condition);

    public Session getSession(String code);
}
