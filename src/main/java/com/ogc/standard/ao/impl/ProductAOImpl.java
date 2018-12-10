package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IProductAO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.ICategoryBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Category;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN629010Req;
import com.ogc.standard.dto.req.XN629010ReqSpecs;
import com.ogc.standard.dto.req.XN629010ReqTree;
import com.ogc.standard.dto.req.XN629011Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECategoryStatus;
import com.ogc.standard.enums.EDirectType;
import com.ogc.standard.enums.EProductStatus;
import com.ogc.standard.enums.ESYSUserKind;
import com.ogc.standard.enums.ESYSUserStatus;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.enums.EUserLevel;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class ProductAOImpl implements IProductAO {

    @Autowired
    private IProductBO productBO;

    @Autowired
    private ICategoryBO categoryBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IApplyBindMaintainBO applyBindMaintainBO;

    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public String addProduct(XN629010Req req) {
        Category category = categoryBO.getCategory(req.getCategoryCode());
        if (ECategoryStatus.PUT_OFF.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "产品分类已下架，请重新选择！");
        }

        // 定向产品
        if (ESellType.DIRECT.getCode().equals(req.getSellType())) {
            if (StringUtils.isBlank(req.getDirectType())
                    || StringUtils.isBlank(req.getDirectObject())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "定向产品方向和针对对象不能为空");
            }
        }

        // 捐赠产品
        if (ESellType.DONATE.getCode().equals(req.getSellType())) {
            if (StringUtils.isBlank(req.getRaiseStartDatetime())
                    && StringUtils.isBlank(req.getRaiseEndDatetime())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "捐赠产品募集开始和募集结束时间不能为空");
            }

            if (StringUtils.isBlank(req.getStartDatetime())
                    && StringUtils.isBlank(req.getEndDatetime())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "捐赠产品认养开始和认养结束时间不能为空");
            }

            if (req.getTreeList().size() > 1) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "捐赠产品只能有一棵树");
            }
        }

        // 集体产品
        if (ESellType.COLLECTIVE.getCode().equals(req.getSellType())) {
            if (StringUtils.isBlank(req.getRaiseCount())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "集体产品认养份数不能为空");
            }

            if (req.getTreeList().size() > 1) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "集体产品只能有一棵树");
            }
        }

        Product product = productBO.saveProduct(req);

        // 添加产品规格
        for (XN629010ReqSpecs productSpecs : req.getProductSpecsList()) {
            // 捐赠产品规格只有一个年限
            if (ESellType.DONATE.getCode().equals(req.getSellType())) {
                productSpecs.setStartDatetime(req.getStartDatetime());
                productSpecs.setEndDatetime(req.getEndDatetime());
            }

            productSpecsBO.saveProductSpecs(product.getCode(), productSpecs);
        }

        // 添加古树
        for (XN629010ReqTree tree : req.getTreeList()) {
            // 判断重复的树
            if (treeBO.isTreeNumberExist(tree.getTreeNumber())) {
                throw new BizException("xn0000",
                    "树木编号" + tree.getTreeNumber() + "已存在，请重新输入！");
            }

            treeBO.saveTree(product, tree);
        }

        // 更新募集数量
        if (ESellType.COLLECTIVE.getCode().equals(req.getSellType())) {
            productBO.refreshRaiseCount(product.getCode(),
                StringValidater.toInteger(req.getRaiseCount()));
        } else {
            productBO.refreshRaiseCount(product.getCode(),
                req.getTreeList().size());
        }

        return product.getCode();
    }

    @Override
    @Transactional
    public void editProduct(XN629011Req req) {
        Category category = categoryBO.getCategory(req.getCategoryCode());
        if (ECategoryStatus.PUT_OFF.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "产品分类已下架，请重新选择！");
        }

        // 定向产品
        if (ESellType.DIRECT.getCode().equals(req.getSellType())) {
            if (StringUtils.isBlank(req.getDirectType())
                    || StringUtils.isBlank(req.getDirectObject())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "定向产品方向和针对对象不能为空");
            }
        }

        // 捐赠产品
        if (ESellType.DONATE.getCode().equals(req.getSellType())) {
            if (StringUtils.isBlank(req.getRaiseStartDatetime())
                    && StringUtils.isBlank(req.getRaiseEndDatetime())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "捐赠产品募集开始和募集结束时间不能为空");
            }

            if (StringUtils.isBlank(req.getStartDatetime())
                    && StringUtils.isBlank(req.getEndDatetime())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "捐赠产品认养开始和认养结束时间不能为空");
            }

            if (req.getTreeList().size() > 1) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "捐赠产品只能有一棵树");
            }
        }

        // 集体产品
        if (ESellType.COLLECTIVE.getCode().equals(req.getSellType())) {
            if (StringUtils.isBlank(req.getRaiseCount())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "集体产品认养份数不能为空");
            }

            if (req.getTreeList().size() > 1) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "集体产品只能有一棵树");
            }
        }

        Product data = productBO.getProduct(req.getCode());

        // 未提交和已下架后可修改
        if (!EProductStatus.DRAFT.getCode().equals(data.getStatus())
                && !EProductStatus.PUTOFFED.getCode().equals(data.getStatus())
                && !EProductStatus.APPROVE_NO.getCode()
                    .equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "产品不处于可修改状态！");
        }

        productBO.refreshProduct(data, req);

        // 删除旧产品规格
        productSpecsBO.removeProductSpecsByProduct(req.getCode());

        // 添加新产品规格
        for (XN629010ReqSpecs productSpecs : req.getProductSpecsList()) {
            // 捐赠产品规格只有一个年限
            if (ESellType.DONATE.getCode().equals(req.getSellType())) {
                productSpecs.setStartDatetime(req.getStartDatetime());
                productSpecs.setEndDatetime(req.getEndDatetime());
            }

            productSpecsBO.saveProductSpecs(req.getCode(), productSpecs);
        }

        // 删除旧古树
        treeBO.removeTreeByProduct(req.getCode());

        // 添加新古树
        for (XN629010ReqTree tree : req.getTreeList()) {
            // 判断重复的树
            if (treeBO.isTreeNumberExist(tree.getTreeNumber())) {
                throw new BizException("xn0000",
                    "树木编号" + tree.getTreeNumber() + "已存在，请重新输入！");
            }

            treeBO.saveTree(data, tree);
        }

        // 更新募集数量
        if (ESellType.COLLECTIVE.getCode().equals(req.getSellType())) {
            productBO.refreshRaiseCount(req.getCode(),
                StringValidater.toInteger(req.getRaiseCount()));
        } else {
            productBO.refreshRaiseCount(req.getCode(),
                req.getTreeList().size());
        }
    }

    @Override
    public void submitProduct(String code, String updater, String remark) {
        // 检查产权方信息是否完善
        SYSUser sysUser = sysUserBO.getSYSUser(updater);
        if (!ESYSUserKind.OWNER.getCode().equals(sysUser.getKind())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "不是产权方用户不能提交产品");
        }

        Company company = companyBO.getCompanyByUserId(updater);
        if (StringUtils.isBlank(company.getContractTemplate())
                || StringUtils.isBlank(company.getCertificateTemplate())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "请先完善合同模板和证书模板信息后方可提交产品");
        }

        if (!ESYSUserStatus.NORMAL.getCode().equals(sysUser.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "产权方未通过审核，无法提交产品");
        }

        // 验证是否绑定养护方
        applyBindMaintainBO.doCheckBindMaintain(sysUser.getUserId());

        Product product = productBO.getProduct(code);
        if (!EProductStatus.DRAFT.getCode().equals(product.getStatus())
                && !EProductStatus.PUTOFFED.getCode()
                    .equals(product.getStatus())
                && !EProductStatus.APPROVE_NO.getCode()
                    .equals(product.getStatus())) {
            throw new BizException("xn0000", "产品未处于可提交状态！");
        }

        productBO.refreshSubmitProduct(code, updater, remark);
    }

    @Override
    public void approveProduct(String code, String approveResult,
            String updater, String remark) {
        Product product = productBO.getProduct(code);

        if (!EProductStatus.TO_APPROVE.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "产品未处于可审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EProductStatus.TO_PUTON.getCode();
        } else {
            status = EProductStatus.APPROVE_NO.getCode();
        }

        productBO.refreshApproveProduct(code, status, updater, remark);
    }

    @Override
    public void putOnProduct(String code, String location, Integer orderNo,
            String updater, String remark) {
        Product product = productBO.getProduct(code);

        if (!EProductStatus.TO_PUTON.getCode().equals(product.getStatus())
                && !EProductStatus.PUTOFFED.getCode()
                    .equals(product.getStatus())) {
            throw new BizException("xn0000", "产品未处于可上架状态！");
        }

        productBO.refreshPutOnProduct(code, location, orderNo, updater, remark);
    }

    @Override
    public void putOffProduct(String code, String updater) {
        Product product = productBO.getProduct(code);

        // 集体产品
        if (ESellType.COLLECTIVE.getCode().equals(product.getSellType())) {
            if (!EProductStatus.TO_ADOPT.getCode().equals(product.getStatus())
                    && !EProductStatus.ADOPT.getCode()
                        .equals(product.getStatus())) {
                throw new BizException("xn0000", "产品未处于可下架状态！");
            }

        } else {

            // 个人、定向、捐赠
            if (!EProductStatus.TO_ADOPT.getCode()
                .equals(product.getStatus())) {
                throw new BizException("xn0000", "产品未处于可下架状态！");
            }
        }

        // 存在认养中的古树时不能下架
        // if (CollectionUtils.isNotEmpty(treeBO.queryTreeListByProduct(code,
        // ETreeStatus.ADOPTED.getCode()))) {
        // throw new BizException("xn0000", "产品中存在认养中的古树，无法下架！");
        // }

        productBO.refreshPutOffProduct(code, updater);
    }

    @Override
    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition) {
        Paginable<Product> page = productBO.getPaginable(start, limit,
            condition);
        List<Product> list = page.getList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Product product : list) {
                initProduct(product);
            }
        }

        return page;
    }

    @Override
    public List<Product> queryProductList(Product condition) {
        return productBO.queryProductList(condition);
    }

    @Override
    public Product getProduct(String code) {
        Product product = productBO.getProduct(code);
        initProduct(product);
        return product;
    }

    private void initProduct(Product product) {
        // 产品规格列表
        List<ProductSpecs> specsList = new ArrayList<ProductSpecs>();
        if (ESellType.COLLECTIVE.getCode().equals(product.getSellType())
                && EProductStatus.LOCKED.getCode()
                    .equals(product.getStatus())) {

            // 集体认养中的产品只查询认养的规格
            ProductSpecs productSpecs = productSpecsBO
                .getProductSpecs(product.getSpecsCode());
            specsList.add(productSpecs);

        } else {
            specsList = productSpecsBO
                .queryProductSpecsListByProduct(product.getCode());
        }
        product.setProductSpecsList(specsList);

        // 捐赠产品认养年限
        if (ESellType.DONATE.getCode().equals(product.getSellType())) {
            product.setStartDatetime(specsList.get(0).getStartDatetime());
            product.setEndDatetime(specsList.get(0).getEndDatetime());
        }

        // 初始化最小价格和最大价格
        BigDecimal minPrice = BigDecimal.ZERO;
        BigDecimal maxPrice = minPrice;
        for (ProductSpecs productSpecs : specsList) {
            if (minPrice.compareTo(BigDecimal.ZERO) == 0) {
                minPrice = productSpecs.getPrice();
            }
            if (productSpecs.getPrice().compareTo(minPrice) < 0) {
                minPrice = productSpecs.getPrice();
            }
            if (productSpecs.getPrice().compareTo(maxPrice) > 0) {
                maxPrice = productSpecs.getPrice();
            }
        }
        product.setMinPrice(minPrice);
        product.setMaxPrice(maxPrice);

        // 古树列表
        List<Tree> treeList = treeBO.queryTreeListByProduct(product.getCode());
        product.setTreeList(treeList);

        // 树木总量获取
        product.setTreeTotalCount(treeList.size());

        // 树木剩余量数量
        product.setTreeRemainCount(treeBO.getTreeCount(product.getCode(),
            ETreeStatus.TO_ADOPT.getCode()));

        // 类型
        Category category = categoryBO.getCategory(product.getCategoryCode());
        product.setCategoryName(category.getName());

        // 产权方信息
        SYSUser ownerInfo = sysUserBO.getSYSUserUnCheck(product.getOwnerId());
        product.setOwnerInfo(ownerInfo);

        // 定向产品的定向用户
        if (ESellType.DIRECT.getCode().equals(product.getSellType())
                && EDirectType.USERS.getCode()
                    .equals(product.getDirectType())) {
            String[] directUser = product.getDirectObject().split(",");
            StringBuffer directObjectName = new StringBuffer();

            int userCount = 1;
            for (String userId : directUser) {
                User user = userBO.getUserUnCheck(userId);
                String userName = null;

                if (null != user) {
                    userName = user.getMobile();
                    if (null != user.getRealName()) {
                        userName = user.getRealName().concat(userName);
                    }
                }

                directObjectName.append(userName);

                if (userCount++ < directUser.length) {
                    directObjectName.append(",");
                }
            }

            product.setDirectObjectName(directObjectName.toString());
        }

        // 定向产品的定向等级
        if (ESellType.DIRECT.getCode().equals(product.getSellType())
                && EDirectType.LEVEL.getCode()
                    .equals(product.getDirectType())) {
            Map<String, EUserLevel> userLevel = EUserLevel.getMap();

            product.setDirectObjectName(
                userLevel.get(product.getDirectObject()).getValue());
        }

        // 集体产品的第一个支付人
        if (ESellType.COLLECTIVE.getCode().equals(product.getSellType())) {
            User firstUser = userBO
                .getUserUnCheck(product.getCollectFirstUser());
            if (null != firstUser) {
                String userName = PhoneUtil.hideMobile(firstUser.getMobile());
                if (null != firstUser.getNickname()) {
                    userName = firstUser.getNickname();
                }

                product.setCollectFirstUserName(userName);
            }
        }

    }
}
