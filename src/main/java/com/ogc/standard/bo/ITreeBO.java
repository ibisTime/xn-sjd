package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629010ReqTree;
import com.ogc.standard.dto.req.XN629400ReqTree;

/**
 * 古树
 * @author: silver 
 * @since: 2018年9月27日 下午3:53:36 
 * @history:
 */
public interface ITreeBO extends IPaginableBO<Tree> {

    // 树木编号是否存在
    public boolean isTreeNumberExist(String treeNumber);

    // 添加古树
    public void saveTree(Tree tree);

    // 添加古树
    public String saveTree(Product product, XN629010ReqTree tree);

    // 添加古树
    public String saveTree(PresellProduct presellProduct, XN629400ReqTree tree);

    // 删除产品下的古树
    public void removeTreeByProduct(String productCode);

    // 修改古树
    public void refreshTree(Tree data);

    // 认养古树
    public void refreshToPayTree(Tree tree, String curOrderCode);

    // 取消认养
    public void refreshCancelTree(Tree tree);

    // 取消认养
    public void refreshCancelTreeByProduct(String productCode);

    // 支付认养
    public void refreshPayTree(Tree tree, Integer adoptCount);

    // 更新文章数
    public void refreshArticleCount(String treeNumber, Integer articleCount);

    // 更新点赞数
    public void refreshPointCount(String treeNumber, Integer pointCount);

    // 更新收藏数
    public void refreshCollectionCount(String treeNumber,
            Integer collectionCount);

    // 更新认养数量
    public void refreshAdoptCount(String treeNumber, Integer adoptCount);

    // 更新认养数量
    public void refreshAdoptCountByProduct(String productCode,
            Integer adoptCount);

    // 根据树木编号获取树
    public Tree getTreeByTreeNumber(String treeNumber);

    // 获取产品下的古树
    public List<Tree> queryTreeListByProduct(String productCode);

    // 获取产品下某个状态的古树
    public List<Tree> queryTreeListByProduct(String productCode, String status);

    // 获取树木总量
    public int getTreeCount(String productCode);

    // 获取树木各个状态总量
    public int getTreeCount(String productCode, String status);

    // 查询指定产权方的古树数量
    public long getTotalCountByOwnerId(String ownerId);

    // 查询预售产品中还有剩余产量的树
    public List<Tree> queryTreeListRemainInventory(String productCode,
            Integer maxAdoptCount);

    public List<Tree> queryTreeListByOrderCode(String orderCode);

    public List<Tree> queryTreeList(Tree condition);

    public Tree getTree(String code);
}
