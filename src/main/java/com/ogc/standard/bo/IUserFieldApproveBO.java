/**
 * @Title IUserFieldApproveBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午12:44:20 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.UserFieldApprove;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 下午12:44:20 
 * @history:
 */
public interface IUserFieldApproveBO extends IPaginableBO<UserFieldApprove> {
    // 提交申请
    public void saveApply(String userId, String idHold, String field,
            String captcha, String type);

    // 审核
    public void refreshApprove(UserFieldApprove data, String approveUser,
            String remark);

    public void checkApproveStatus(String applyUser);

    public UserFieldApprove getUserFieldApprove(Long id);
}
