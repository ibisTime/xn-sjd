/**
 * @Title PostageTemplateDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午1:39:27 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IPostageTemplateDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.PostageTemplate;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午1:39:27 
 * @history:
 */
@Repository("postageTemplateDAOImpl")
public class PostageTemplateDAOImpl extends AMybatisTemplate implements
        IPostageTemplateDAO {

    @Override
    public int insert(PostageTemplate data) {
        return super.insert(NAMESPACE.concat("insert_postageTemplate"), data);
    }

    @Override
    public int delete(PostageTemplate data) {
        return super.delete(NAMESPACE.concat("delete_postageTemplate"), data);
    }

    @Override
    public PostageTemplate select(PostageTemplate condition) {
        return super.select(NAMESPACE.concat("select_postageTemplate"),
            condition, PostageTemplate.class);
    }

    @Override
    public long selectTotalCount(PostageTemplate condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_postageTemplate_count"), condition);
    }

    @Override
    public List<PostageTemplate> selectList(PostageTemplate condition) {
        return super.selectList(NAMESPACE.concat("select_postageTemplate"),
            condition, PostageTemplate.class);
    }

    @Override
    public List<PostageTemplate> selectList(PostageTemplate condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_postageTemplate"),
            start, count, condition, PostageTemplate.class);
    }

    @Override
    public int updateTemplate(PostageTemplate data) {
        return super.update(NAMESPACE.concat("update_postageTemplate"), data);
    }

}
