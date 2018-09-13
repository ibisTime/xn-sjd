/**
 * @Title IUserIdAuthBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午5:15:21 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.UserIdAuth;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 下午5:15:21 
 * @history:
 */
public interface IUserIdAuthBO extends IPaginableBO<UserIdAuth> {
    // 提交申请
    public void saveApply(UserIdAuth data);

    // 审核
    public void refreshApprove(UserIdAuth data);

    public List<UserIdAuth> queryUserIdAuthList(UserIdAuth condition);

    public UserIdAuth getUserIdAuth(Long id);

    public UserIdAuth getUserIdAuthWithNoCheck(Long id);
}
