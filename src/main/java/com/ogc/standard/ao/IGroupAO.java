package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Group;

public interface IGroupAO {
    static final String DEFAULT_ORDER_COLUMN = "order_no";

    public Paginable<Group> queryGroupPage(int start, int limit,
            Group condition);

    public List<Group> queryGroupList(Group condition);

    public Group getGroup(String code);

    /**
     * 详情查询组合，并根据访客UserId获取是否关注、提醒
     * @param code
     * @param visitUserId
     * @return 
     * @create: 2018年8月21日 下午9:44:14 lei
     * @history:
     */
    public Group getGroupByVisitUserId(String code, String visitUserId);

    /**
     * 分页查询我关注的组合
     * @param start
     * @param pageSize
     * @param userId
     * @return 
     * @create: 2018年8月21日 下午9:43:41 lei
     * @history:
     */
    public Paginable<Group> queryMyAttentionGroupPage(int start, int pageSize,
            String userId);

}
