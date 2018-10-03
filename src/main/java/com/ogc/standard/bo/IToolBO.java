package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.dto.req.XN629502Req;

public interface IToolBO extends IPaginableBO<Tool> {

    public void refreshUp(Tool tool, String orderNo, String updater,
            String remark);

    public void refreshDown(Tool tool, String updater, String remark);

    public void refreshTool(Tool data, XN629502Req req);

    public List<Tool> queryToolList(Tool condition);

    public Tool getTool(String code);

}
