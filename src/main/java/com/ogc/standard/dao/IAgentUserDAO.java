package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AgentUser;

/**
 * 代理用户
 * @author: silver 
 * @since: 2018年9月28日 上午11:38:52 
 * @history:
 */
public interface IAgentUserDAO extends IBaseDAO<AgentUser> {
    String NAMESPACE = IAgentUserDAO.class.getName().concat(".");

    // 设置支付密码
    public int updateTradePwd(AgentUser data);

    // 设置登录密码
    public int updateLoginPwd(AgentUser data);

    // 更新手机号
    public int updateMobile(AgentUser data);

    // 更新父节点用户编号
    public int updateToApprove(AgentUser data);

    // 更新真实姓名
    public int updateRealName(AgentUser data);

    // 更新状态
    public int updateStatus(AgentUser data);

    // 更新头像
    public int updatePhoto(AgentUser data);

}
