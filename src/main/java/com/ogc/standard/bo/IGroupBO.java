package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Group;

public interface IGroupBO extends IPaginableBO<Group> {

    public String saveGroup(String matchCode, String teamCode, String userId,
            BigDecimal initAmount);

    public void editGroupFollowNumber(String code, int followNumber);

    public int removeGroup(String code);

    public List<Group> queryGroupList(Group condition);

    public Group getGroup(String code);

    public Group getGroupByVisitUserId(String code, String visitUserId);

}
