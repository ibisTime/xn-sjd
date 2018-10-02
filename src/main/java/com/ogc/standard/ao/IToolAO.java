package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.dto.req.XN629502Req;

@Component
public interface IToolAO {
    static final String DEFAULT_ORDER_COLUMN = "order_no";

    public int editTool(XN629502Req req);

    public Paginable<Tool> queryToolPage(int start, int limit, Tool condition);

    public List<Tool> queryToolList(Tool condition);

    public Tool getTool(String code);

    public void putUp(String code, String updater, String remark);

    public void getDown(String code, String updater, String remark);

}
