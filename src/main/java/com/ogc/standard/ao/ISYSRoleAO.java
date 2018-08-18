package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SYSRole;
import com.ogc.standard.dto.req.XN630000Req;
import com.ogc.standard.dto.req.XN630002Req;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午9:18:16 
 * @history:
 */
public interface ISYSRoleAO {
    String DEFAULT_ORDER_COLUMN = "code";

    public String addSYSRole(XN630000Req req);

    public boolean dropSYSRole(String roleCode);

    public boolean editSYSRole(XN630002Req req);

    public List<SYSRole> querySYSRoleList(SYSRole condition);

    public Paginable<SYSRole> querySYSRolePage(int start, int limit,
            SYSRole condition);

    public SYSRole getSYSRole(String code);

}
