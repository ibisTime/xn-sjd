package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISignLogBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISignLogDAO;
import com.ogc.standard.domain.SignLog;
import com.ogc.standard.enums.ESignLogClient;
import com.ogc.standard.enums.ESignLogType;

@Component
public class SignLogBOImpl extends PaginableBOImpl<SignLog>
        implements ISignLogBO {

    @Autowired
    private ISignLogDAO signLogDAO;

    @Override
    public boolean isSignLogExist(Integer id) {
        SignLog condition = new SignLog();
        condition.setId(id);
        if (signLogDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveSignLog(SignLog data) {
        String code = null;

        data.setType(ESignLogType.LOGIN.getCode());
        data.setIp("");
        data.setClient(ESignLogClient.ANDROID.getCode());
        data.setLocation("location");
        data.setCreateDatetime(new Date());
        signLogDAO.insert(data);
        return code;
    }

    @Override
    public List<SignLog> querySignLogList(SignLog condition) {
        return signLogDAO.selectList(condition);
    }

}
