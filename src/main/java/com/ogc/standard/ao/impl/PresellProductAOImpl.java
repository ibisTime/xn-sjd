package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IPresellProductAO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.ICategoryBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.IPresellSpecsBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Category;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN629400Req;
import com.ogc.standard.dto.req.XN629400ReqSpecs;
import com.ogc.standard.dto.req.XN629400ReqTree;
import com.ogc.standard.dto.req.XN629401Req;
import com.ogc.standard.dto.req.XN629401ReqSpecs;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECategoryStatus;
import com.ogc.standard.enums.EPresellProductStatus;
import com.ogc.standard.enums.ESYSUserKind;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class PresellProductAOImpl implements IPresellProductAO {

    @Autowired
    private IPresellProductBO presellProductBO;

    @Autowired
    private ICategoryBO categoryBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IApplyBindMaintainBO applyBindMaintainBO;

    @Autowired
    private IPresellSpecsBO presellSpecsBO;

    @Autowired
    private ITreeBO treeBO;

    @Override
    @Transactional
    public String addPresellProduct(XN629400Req req) {
        Category category = categoryBO.getCategory(req.getCategoryCode());
        if (ECategoryStatus.PUT_OFF.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "产品分类已下架，请重新选择！");
        }

        // 添加产品
        PresellProduct presellProduct = presellProductBO
            .savePresellProduct(req);

        // 添加规格
        for (XN629400ReqSpecs presellSpecs : req.getPresellSpecsList()) {
            presellSpecsBO.savePresellSpecs(presellProduct.getCode(),
                presellSpecs.getPackCount(), presellSpecs.getPackCount(),
                presellSpecs.getPrice(), presellSpecs.getIncrease());
        }

        // 添加古树
        for (XN629400ReqTree tree : req.getTreeList()) {
            // 判断重复的树
            if (treeBO.isTreeNumberExist(tree.getTreeNumber())) {
                throw new BizException("xn0000",
                    "树木编号" + tree.getTreeNumber() + "已存在，请重新输入！");
            }

            treeBO.saveTree(presellProduct, tree);
        }

        return presellProduct.getCode();
    }

    @Override
    @Transactional
    public void editPresellProduct(XN629401Req req) {
        Category category = categoryBO.getCategory(req.getCategoryCode());
        if (ECategoryStatus.PUT_OFF.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "产品分类已下架，请重新选择！");
        }

        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(req.getCode());
        if (!EPresellProductStatus.DRAFT.getCode()
            .equals(presellProduct.getStatus())
                && !EPresellProductStatus.PUTOFFED.getCode()
                    .equals(presellProduct.getStatus())
                && !EPresellProductStatus.APPROVE_NO.getCode()
                    .equals(presellProduct.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "产品不处于可修改状态！");
        }

        // 更新产品
        presellProductBO.refreshEditPresellProduct(req);

        // 删除旧规格
        presellSpecsBO.removePresellSpecsByProduct(req.getCode());

        // 添加新规格
        for (XN629401ReqSpecs presellSpecs : req.getPresellSpecsList()) {
            presellSpecsBO.savePresellSpecs(req.getCode(),
                presellSpecs.getPackCount(), presellSpecs.getPackCount(),
                presellSpecs.getPrice(), presellSpecs.getIncrease());
        }

        // 删除旧的古树
        treeBO.removeTreeByProduct(req.getCode());

        // 添加古树
        for (XN629400ReqTree tree : req.getTreeList()) {
            // 判断重复的树
            if (treeBO.isTreeNumberExist(tree.getTreeNumber())) {
                throw new BizException("xn0000",
                    "树木编号" + tree.getTreeNumber() + "已存在，请重新输入！");
            }

            treeBO.saveTree(presellProduct, tree);
        }
    }

    @Override
    public void submitPresellProduct(String code, String updater,
            String remark) {
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

        // 验证是否绑定养护方
        applyBindMaintainBO.doCheckBindMaintain(sysUser.getUserId());

        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(code);
        if (!EPresellProductStatus.DRAFT.getCode()
            .equals(presellProduct.getStatus())) {
            throw new BizException("xn0000", "产品未处于可提交状态！");
        }

        presellProductBO.refreshSubmitPresellProduct(code, updater, remark);
    }

    @Override
    public void approvePresellProduct(String code, String approveResult,
            String updater, String remark) {
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(code);

        if (!EPresellProductStatus.TO_APPROVE.getCode()
            .equals(presellProduct.getStatus())) {
            throw new BizException("xn0000", "产品未处于可审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EPresellProductStatus.TO_PUTON.getCode();
        } else {
            status = EPresellProductStatus.APPROVE_NO.getCode();
        }

        presellProductBO.refreshApprovePresellProduct(code, status, updater,
            remark);
    }

    @Override
    public void putOnPresellProduct(String code, String location,
            Integer orderNo, String updater) {
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(code);

        if (!EPresellProductStatus.TO_PUTON.getCode()
            .equals(presellProduct.getStatus())
                && !EPresellProductStatus.PUTOFFED.getCode()
                    .equals(presellProduct.getStatus())) {
            throw new BizException("xn0000", "产品未处于可上架状态！");
        }

        presellProductBO.refreshPutOnPresellProduct(code, location, orderNo,
            updater);
    }

    @Override
    public void putOffPresellProduct(String code, String updater) {
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(code);

        if (!EPresellProductStatus.TO_ADOPT.getCode()
            .equals(presellProduct.getStatus())) {
            throw new BizException("xn0000", "产品未处于可下架状态！");
        }

        presellProductBO.refreshPutOffPresellProduct(code, updater);
    }

    @Override
    public Paginable<PresellProduct> queryPresellProductPage(int start,
            int limit, PresellProduct condition) {
        return presellProductBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<PresellProduct> queryPresellProductList(
            PresellProduct condition) {
        return presellProductBO.queryPresellProductList(condition);
    }

    @Override
    public PresellProduct getPresellProduct(String code) {
        return presellProductBO.getPresellProduct(code);
    }

}
