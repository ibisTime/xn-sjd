/**
 * @Title IPostageTemplate.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午2:14:43 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.PostageTemplate;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午2:14:43 
 * @history:
 */
public interface IPostageTemplateAO {

    public String addTemplate(String shopCode, String deliverPlace,
            String receivePlace, BigDecimal price, String updater, String remark);

    public void dropTemplate(String code);

    public void editPostFee(String code, BigDecimal price, String updater,
            String remark);

    public Paginable<PostageTemplate> queryTemplatePage(int start, int limit,
            PostageTemplate condition);

    public List<PostageTemplate> queryTemplateList(PostageTemplate condition);

    public PostageTemplate getPostageTemplate(String code);
}
