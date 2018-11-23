/**
 * @Title PostageTemplateBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午1:53:01 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IPostageTemplateBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IPostageTemplateDAO;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.PostageTemplate;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午1:53:01 
 * @history:
 */
@Component
public class PostageTemplateBOImpl extends PaginableBOImpl<PostageTemplate>
        implements IPostageTemplateBO {

    @Autowired
    private IPostageTemplateDAO postageTemplateDAO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String saveTemplate(String shopCode, String deliverPlace,
            String receivePlace, BigDecimal price, String updater,
            String remark) {
        PostageTemplate data = new PostageTemplate();
        Company shop = companyBO.getCompany(shopCode);

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.PostageTemplate.getCode());
        data.setCode(code);
        data.setShopCode(shopCode);
        data.setShopName(shop.getName());
        data.setDeliverPlace(deliverPlace);
        data.setReceiverPlace(receivePlace);

        data.setPrice(price);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        postageTemplateDAO.insert(data);
        return code;
    }

    @Override
    public void removeTemplate(String code) {
        PostageTemplate data = new PostageTemplate();
        data.setCode(code);
        postageTemplateDAO.delete(data);
    }

    @Override
    public void refreshTemplate(PostageTemplate data, BigDecimal price,
            String updater, String remark) {
        data.setPrice(price);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        postageTemplateDAO.updateTemplate(data);
    }

    @Override
    public List<PostageTemplate> queryTemplateList(PostageTemplate condition) {
        return postageTemplateDAO.selectList(condition);
    }

    @Override
    public PostageTemplate getPostageTemplate(String code) {
        PostageTemplate condition = new PostageTemplate();
        condition.setCode(code);
        PostageTemplate data = postageTemplateDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "该邮费模版不存在");
        }
        return data;
    }

    @Override
    public boolean isTemplateExist(String shopCode, String deliverPlace,
            String receivePlace) {
        PostageTemplate condition = new PostageTemplate();
        condition.setShopCode(shopCode);
        condition.setDeliverPlace(deliverPlace);
        condition.setReceiverPlace(receivePlace);
        if (postageTemplateDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

}
