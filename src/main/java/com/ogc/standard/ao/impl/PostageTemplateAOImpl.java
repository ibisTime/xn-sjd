/**
 * @Title PostageTemplateAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午2:21:19 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IPostageTemplateAO;
import com.ogc.standard.bo.IPostageTemplateBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.PostageTemplate;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午2:21:19 
 * @history:
 */
@Service
public class PostageTemplateAOImpl implements IPostageTemplateAO {

    @Autowired
    private IPostageTemplateBO postageTemplateBO;

    @Override
    public String addTemplate(String shopCode, String deliverPlace,
            String receivePlace, BigDecimal price, String updater,
            String remark) {
        if (postageTemplateBO.isTemplateExist(shopCode, deliverPlace,
            receivePlace)) {
            throw new BizException("xn0000", "已存在该邮费模版");
        }

        return postageTemplateBO.saveTemplate(shopCode, deliverPlace,
            receivePlace, price, updater, remark);
    }

    @Override
    public void dropTemplate(String code) {
        // 判断是否存在
        postageTemplateBO.getPostageTemplate(code);
        // 删除
        postageTemplateBO.removeTemplate(code);
    }

    @Override
    public void editPostFee(String code, BigDecimal price, String updater,
            String remark) {
        PostageTemplate data = postageTemplateBO.getPostageTemplate(code);
        postageTemplateBO.refreshTemplate(data, price, updater, remark);

    }

    @Override
    public Paginable<PostageTemplate> queryTemplatePage(int start, int limit,
            PostageTemplate condition) {
        Paginable<PostageTemplate> page = postageTemplateBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (PostageTemplate postageTemplate : page.getList()) {
                init(postageTemplate);
            }
        }

        return page;
    }

    @Override
    public List<PostageTemplate> queryTemplateList(PostageTemplate condition) {
        List<PostageTemplate> list = postageTemplateBO
            .queryTemplateList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (PostageTemplate postageTemplate : list) {
                init(postageTemplate);
            }
        }

        return list;
    }

    @Override
    public PostageTemplate getPostageTemplate(String code) {
        PostageTemplate postageTemplate = postageTemplateBO
            .getPostageTemplate(code);

        init(postageTemplate);

        return postageTemplate;
    }

    private void init(PostageTemplate postageTemplate) {
    }

}
