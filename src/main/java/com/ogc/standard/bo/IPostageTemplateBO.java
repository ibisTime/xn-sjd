/**
 * @Title IPostageTemplateBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午1:47:07 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.PostageTemplate;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午1:47:07 
 * @history:
 */
public interface IPostageTemplateBO extends IPaginableBO<PostageTemplate> {

    public String saveTemplate(String shopCode, String deliverPlace,
            String receivePlace, BigDecimal price, String updater,
            String remark);

    public void removeTemplate(String code);

    public void refreshTemplate(PostageTemplate data, BigDecimal price,
            String updater, String remark);

    public List<PostageTemplate> queryTemplateList(PostageTemplate condition);

    public PostageTemplate getPostageTemplate(String shopCode,
            String deliverPlace, String receivePlace);

    public PostageTemplate getPostageTemplate(String code);

    public boolean isTemplateExist(String shopCode, String deliverPlace,
            String receivePlace);
}
