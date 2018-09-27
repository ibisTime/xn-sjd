package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629010ReqTree;

/**
 * 古树
 * @author: silver 
 * @since: 2018年9月27日 下午3:53:36 
 * @history:
 */
public interface ITreeBO extends IPaginableBO<Tree> {

    public boolean isTreeExist(String code);

    // 添加古树
    public String saveTree(Tree tree);

    // 添加古树
    public String saveTree(Product product, XN629010ReqTree tree);

    // 删除产品下的古树
    public void removeTreeByProduct(String productCode);

    // 修改古树
    public void refreshTree(Tree data);

    public List<Tree> queryTreeList(Tree condition);

    public Tree getTree(String code);

}
