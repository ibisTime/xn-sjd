package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Visitor;

/**
 * 来访人
 * @author: silver 
 * @since: Oct 5, 2018 4:11:36 PM 
 * @history:
 */
public interface IVisitorBO extends IPaginableBO<Visitor> {

    // 添加来访记录
    public String saveVisitor(String adoptTreeCode, String userId);

    // 删除来访记录
    public int removeVisitor(String code);

    // 前10条是否存在访问记录
    public Visitor getTopTenVisitor(String adoptTreeCode, String userId);

    public List<Visitor> queryVisitorList(Visitor condition);

    public Visitor getVisitor(String code);

}
