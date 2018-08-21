package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.MatchApply;

/**
 * 参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午2:28:11 
 * @history:
 */
public interface IMatchApplyDAO extends IBaseDAO<MatchApply> {
    String NAMESPACE = IMatchApplyDAO.class.getName().concat(".");

    // 审核参赛申请
    public int updateApprove(MatchApply data);

}
