package com.ogc.standard.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IBizLogDAO;
import com.ogc.standard.domain.BizLog;
import com.ogc.standard.exception.BizException;

@Component
public class BizLogBOImpl extends PaginableBOImpl<BizLog> implements IBizLogBO {

    @Autowired
    private IBizLogDAO bizLogDAO;

    @Override
    public int saveBizLog(BizLog data) {
        Integer id = null;
        if (data != null) {
            id = bizLogDAO.insert(data);
        }
        return id;
    }

    @Override
    public List<BizLog> queryBizLogList(BizLog condition) {
        return bizLogDAO.selectList(condition);
    }

    @Override
    public BizLog getBizLog(int id) {
        BizLog data = null;
        if (id != 0) {
            BizLog condition = new BizLog();
            condition.setId(id);
            data = bizLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "日志编号不存在");
            }
        }
        return data;
    }
}
