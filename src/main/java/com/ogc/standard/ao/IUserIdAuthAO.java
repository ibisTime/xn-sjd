/**
 * @Title IUserIdAuthAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午5:25:36 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.UserIdAuth;
import com.ogc.standard.dto.req.XN805160Req;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 下午5:25:36 
 * @history:
 */
public interface IUserIdAuthAO {
    public void apply(XN805160Req req);

    public void approve(Long id, String approveResult, String approveUser,
            String remark);

    public UserIdAuth getUserIdAuth(Long id);

    public List<UserIdAuth> getUserIdAuthByUserId(String userId);

    public Paginable<UserIdAuth> queryUserIdAuthPage(int start, int limit,
            UserIdAuth condition);
}
