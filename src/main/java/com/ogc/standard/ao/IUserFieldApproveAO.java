/**
 * @Title IUserFieldApproveAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午2:17:26 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.UserFieldApprove;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 下午2:17:26 
 * @history:
 */
public interface IUserFieldApproveAO {

    public void apply(String userId, String idHold, String field,
            String captcha, String type);

    public void approve(Long id, String approveResult, String approveUser,
            String remark);

    public UserFieldApprove getUserFieldApprove(Long id);

    public Paginable<UserFieldApprove> queryUserFieldApprovePage(int start,
            int limit, UserFieldApprove condition);
}
