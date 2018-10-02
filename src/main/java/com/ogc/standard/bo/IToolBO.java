package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.dto.req.XN629502Req;

public interface IToolBO extends IPaginableBO<Tool> {

    public int removeTool(String code);

    public int refreshTool(Tool data, XN629502Req req);

    public List<Tool> queryToolList(Tool condition);

    public Tool getTool(String code);

    public void refreshStatus(Tool tool, String updater, String remark);

}
