package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IDeriveGroupBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IDeriveGroupDAO;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.enums.EDeriveGroupStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EPresellType;
import com.ogc.standard.exception.BizException;

@Component
public class DeriveGroupBOImpl extends PaginableBOImpl<DeriveGroup>
        implements IDeriveGroupBO {

    @Autowired
    private IDeriveGroupDAO deriveGroupDAO;

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Autowired
    private IPresellProductBO presellProductBO;

    @Override
    public String saveDirectSales(String originalCode, BigDecimal price,
            Integer quantity, String claimant) {
        DeriveGroup deriveGroup = new DeriveGroup();
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(originalCode);
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(originalGroup.getProductCode());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.DERIVE_GROUP.getCode());
        deriveGroup.setCode(code);
        deriveGroup.setOriginalCode(originalCode);
        deriveGroup.setProductCode(originalGroup.getProductCode());
        deriveGroup.setProductName(originalGroup.getProductName());
        deriveGroup.setType(EPresellType.DIRECT.getCode());

        deriveGroup.setPrice(price);
        deriveGroup.setQuantity(quantity);
        deriveGroup.setUnit(presellProduct.getPackUnit());
        deriveGroup.setCreater(originalGroup.getOwnerId());
        deriveGroup.setCreateDatetime(new Date());

        deriveGroup.setStatus(EDeriveGroupStatus.TO_CLAIM.getCode());
        deriveGroup.setClaimant(claimant);
        deriveGroupDAO.insert(deriveGroup);

        return code;
    }

    @Override
    public String saveQrSales(String originalCode, BigDecimal price,
            Integer quantity) {
        DeriveGroup deriveGroup = new DeriveGroup();
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(originalCode);
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(originalGroup.getProductCode());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.DERIVE_GROUP.getCode());
        deriveGroup.setCode(code);
        deriveGroup.setOriginalCode(originalCode);
        deriveGroup.setProductCode(originalGroup.getProductCode());
        deriveGroup.setProductName(originalGroup.getProductName());
        deriveGroup.setType(EPresellType.QR.getCode());

        deriveGroup.setPrice(originalGroup.getPrice());
        deriveGroup.setQuantity(quantity);
        deriveGroup.setUnit(presellProduct.getPackUnit());
        deriveGroup.setCreater(originalGroup.getOwnerId());
        deriveGroup.setCreateDatetime(new Date());

        deriveGroup.setStatus(EDeriveGroupStatus.TO_CLAIM.getCode());
        // TODO url, rang
        deriveGroupDAO.insert(deriveGroup);

        return code;
    }

    @Override
    public String savePublicSales(String originalCode, BigDecimal price,
            Integer quantity) {
        DeriveGroup deriveGroup = new DeriveGroup();
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(originalCode);
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(originalGroup.getProductCode());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.DERIVE_GROUP.getCode());
        deriveGroup.setCode(code);
        deriveGroup.setOriginalCode(originalCode);
        deriveGroup.setProductCode(originalGroup.getProductCode());
        deriveGroup.setProductName(originalGroup.getProductName());
        deriveGroup.setType(EPresellType.PUBLIC.getCode());

        deriveGroup.setPrice(price);
        deriveGroup.setQuantity(quantity);
        deriveGroup.setUnit(presellProduct.getPackUnit());
        deriveGroup.setCreater(originalGroup.getOwnerId());
        deriveGroup.setCreateDatetime(new Date());

        deriveGroup.setStatus(EDeriveGroupStatus.TO_CLAIM.getCode());
        deriveGroupDAO.insert(deriveGroup);

        return code;
    }

    @Override
    public void refreshRevock(String code, String claimant, String remark) {
        DeriveGroup deriveGroup = new DeriveGroup();

        deriveGroup.setCode(code);
        deriveGroup.setClaimant(claimant);
        deriveGroup.setClaimDatetime(new Date());
        deriveGroup.setStatus(EDeriveGroupStatus.CANCELED.getCode());
        deriveGroup.setRemark(remark);

        deriveGroupDAO.updateRevock(deriveGroup);
    }

    @Override
    public void refreshClaimDirect(String code) {
        DeriveGroup deriveGroup = new DeriveGroup();

        deriveGroup.setCode(code);
        deriveGroup.setClaimDatetime(new Date());
        deriveGroup.setStatus(EDeriveGroupStatus.CLAIMED.getCode());

        deriveGroupDAO.updateClaimDirect(deriveGroup);
    }

    @Override
    public void refreshRejectDirect(String code, String remark) {
        DeriveGroup deriveGroup = new DeriveGroup();

        deriveGroup.setCode(code);
        deriveGroup.setClaimDatetime(new Date());
        deriveGroup.setStatus(EDeriveGroupStatus.REJECTED.getCode());
        deriveGroup.setRemark(remark);

        deriveGroupDAO.updateRejectDirect(deriveGroup);
    }

    @Override
    public void refreshClaimQr(String code, String claimant) {
        DeriveGroup deriveGroup = new DeriveGroup();

        deriveGroup.setCode(code);
        deriveGroup.setClaimant(claimant);
        deriveGroup.setClaimDatetime(new Date());
        deriveGroup.setStatus(EDeriveGroupStatus.CLAIMED.getCode());

        deriveGroupDAO.updateClaimQr(deriveGroup);
    }

    @Override
    public void refreshClaimPublic(String code, String claimant,
            Integer quantity, String status) {
        DeriveGroup deriveGroup = new DeriveGroup();

        deriveGroup.setCode(code);
        deriveGroup.setQuantity(quantity);
        deriveGroup.setClaimant(claimant);
        deriveGroup.setClaimDatetime(new Date());
        deriveGroup.setStatus(status);

        deriveGroupDAO.updateClaimQr(deriveGroup);
    }

    @Override
    public void refreshQuantity(String code, Integer quantity) {
        DeriveGroup deriveGroup = new DeriveGroup();

        deriveGroup.setCode(code);
        deriveGroup.setQuantity(quantity);

        deriveGroupDAO.updateQuantity(deriveGroup);
    }

    @Override
    public List<DeriveGroup> queryDeriveGroupListByCreater(String creater,
            String status) {
        DeriveGroup condition = new DeriveGroup();
        condition.setCreater(creater);
        condition.setStatus(status);
        return deriveGroupDAO.selectList(condition);
    }

    @Override
    public List<DeriveGroup> queryDeriveGroupListDirect(String claimant,
            String status) {
        DeriveGroup condition = new DeriveGroup();
        condition.setType(EPresellType.DIRECT.getCode());
        condition.setClaimant(claimant);
        condition.setStatus(status);
        return deriveGroupDAO.selectList(condition);
    }

    @Override
    public List<DeriveGroup> queryDeriveGroupList(DeriveGroup condition) {
        return deriveGroupDAO.selectList(condition);
    }

    @Override
    public DeriveGroup getDeriveGroup(String code) {
        DeriveGroup data = null;
        if (StringUtils.isNotBlank(code)) {
            DeriveGroup condition = new DeriveGroup();
            condition.setCode(code);
            data = deriveGroupDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "预售派生组不存在");
            }
        }
        return data;
    }

}
