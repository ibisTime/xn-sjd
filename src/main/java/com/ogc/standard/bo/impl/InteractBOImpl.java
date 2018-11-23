package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IInteractBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IInteractDAO;
import com.ogc.standard.domain.Interact;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/**
 * 点赞收藏表
 * @author: silver 
 * @since: 2018年8月22日 下午9:01:52 
 * @history:
 */
@Component
public class InteractBOImpl extends PaginableBOImpl<Interact>
        implements IInteractBO {

    @Autowired
    private IInteractDAO interactDAO;

    @Override
    public boolean isInteractExist(String code) {
        Interact condition = new Interact();
        condition.setCode(code);
        if (interactDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveInteract(String type, String objectType,
            String objectCode, String userId) {
        Interact data = new Interact();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Interact.getCode());
        data.setCode(code);
        data.setType(type);
        data.setObjectType(objectType);
        data.setObjectCode(objectCode);
        data.setUserId(userId);

        data.setCreateDatetime(new Date());
        interactDAO.insert(data);
        return code;

    }

    @Override
    public void removeInteract(String code) {
        Interact data = new Interact();
        data.setCode(code);
        interactDAO.delete(data);
    }

    @Override
    public List<Interact> queryInteractList(String userId, String type,
            String objectType) {
        Interact condition = new Interact();

        condition.setUserId(userId);
        condition.setType(type);
        condition.setObjectType(objectType);

        return interactDAO.selectList(condition);
    }

    @Override
    public List<Interact> queryInteractList(Interact condition) {
        return interactDAO.selectList(condition);
    }

    @Override
    public Interact getInteract(String code) {
        Interact data = null;
        if (StringUtils.isNotBlank(code)) {
            Interact condition = new Interact();
            condition.setCode(code);
            data = interactDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "点赞记录不存在");
            }
        }
        return data;
    }

    @Override
    public Interact getInteract(String type, String objectType,
            String objectCode, String userId) {
        Interact data = null;
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(objectType)
                && StringUtils.isNotBlank(objectCode)
                && StringUtils.isNotBlank(userId)) {

            Interact condition = new Interact();
            condition.setType(type);
            condition.setObjectType(objectType);
            condition.setObjectCode(objectCode);
            condition.setUserId(userId);

            data = interactDAO.select(condition);
        }
        return data;
    }

}
