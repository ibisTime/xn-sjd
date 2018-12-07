package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IAgentUserDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.AgentUser;

@Repository("agentUserDAOImpl")
public class AgentUserDAOImpl extends AMybatisTemplate
        implements IAgentUserDAO {

    @Override
    public int insert(AgentUser data) {
        return super.insert(NAMESPACE.concat("insert_agentUser"), data);
    }

    @Override
    public int updateEdit(AgentUser agentUser) {
        return super.update(NAMESPACE.concat("update_edit"), agentUser);
    }

    @Override
    public int updateTradePwd(AgentUser data) {
        return super.update(NAMESPACE.concat("update_tradePwd"), data);
    }

    @Override
    public int updateLoginPwd(AgentUser data) {
        return super.update(NAMESPACE.concat("update_loginPwd"), data);
    }

    @Override
    public int updateMobile(AgentUser data) {
        return super.update(NAMESPACE.concat("update_mobile"), data);
    }

    @Override
    public int updateRealName(AgentUser data) {
        return super.update(NAMESPACE.concat("update_realName"), data);
    }

    @Override
    public int updateToApprove(AgentUser data) {
        return super.update(NAMESPACE.concat("update_toApprove"), data);
    }

    @Override
    public int updateStatus(AgentUser data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updatePass(AgentUser data) {
        return super.update(NAMESPACE.concat("update_pass"), data);
    }

    @Override
    public int updatePhoto(AgentUser data) {
        return super.update(NAMESPACE.concat("update_photo"), data);
    }

    @Override
    public int delete(AgentUser data) {
        return super.delete(NAMESPACE.concat("delete_agentUser"), data);
    }

    @Override
    public AgentUser select(AgentUser condition) {
        return super.select(NAMESPACE.concat("select_agentUser"), condition,
            AgentUser.class);
    }

    @Override
    public long selectTotalCount(AgentUser condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_agentUser_count"), condition);
    }

    @Override
    public List<AgentUser> selectList(AgentUser condition) {
        return super.selectList(NAMESPACE.concat("select_agentUser"), condition,
            AgentUser.class);
    }

    @Override
    public List<AgentUser> selectList(AgentUser condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_agentUser"), start,
            count, condition, AgentUser.class);
    }

}
