package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Tree;

/**
 * 古树
 * @author: silver 
 * @since: 2018年9月27日 下午4:06:36 
 * @history:
 */
@Component
public interface ITreeAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加古树
    public String addTree(Tree data);

    // 修改古树
    public void editTree(Tree data);

    public Paginable<Tree> queryTreePage(int start, int limit, Tree condition);

    public List<Tree> queryTreeList(Tree condition);

    public Tree getTree(String code);

}
