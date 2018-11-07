/**
 * @Title IPostageTemplate.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午1:37:18 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.PostageTemplate;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午1:37:18 
 * @history:
 */
public interface IPostageTemplateDAO extends IBaseDAO<PostageTemplate> {
    String NAMESPACE = IPostageTemplateDAO.class.getName().concat(".");

    public int updateTemplate(PostageTemplate data);
}
