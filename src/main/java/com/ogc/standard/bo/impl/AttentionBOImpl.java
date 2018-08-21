package com.ogc.standard.bo.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAttentionBO;
import com.ogc.standard.bo.IGroupBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IAttentionDAO;
import com.ogc.standard.domain.Attention;
import com.ogc.standard.enums.EGeneratePrefix;

@Component
public class AttentionBOImpl extends PaginableBOImpl<Attention>
        implements IAttentionBO {

    @Autowired
    private IAttentionDAO attentionDAO;

    @Autowired
    private IGroupBO groupBO;

    @Override
    public String saveAttention(String userId, String groupCode, String type) {
        String code = null;
        if (null != type) {

            code = OrderNoGenerater
                .generate(EGeneratePrefix.ATTENTION.getCode());

            Attention data = new Attention();
            data.setCode(code);
            data.setType(type);
            data.setUserId(userId);
            data.setGroupCode(groupCode);
            data.setGroupUserId(groupBO.getGroup(groupCode).getUserId());

            data.setCreateDatetime(new Date());
            attentionDAO.insert(data);

        }

        return code;
    }

    @Override
    public int removeAttention(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Attention data = new Attention();
            data.setCode(code);
            count = attentionDAO.delete(data);
        }
        return count;
    }

    @Override
    public Attention getAttention(String userId, String groupCode,
            String type) {
        Attention attention = null;

        Attention condition = new Attention();
        condition.setUserId(userId);
        condition.setGroupCode(groupCode);
        condition.setType(type);
        attention = attentionDAO.select(condition);

        return attention;
    }

}
