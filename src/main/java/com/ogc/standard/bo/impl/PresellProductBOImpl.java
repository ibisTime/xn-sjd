package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dao.IPresellProductDAO;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.dto.req.XN629400Req;
import com.ogc.standard.dto.req.XN629401Req;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EPresellProductStatus;
import com.ogc.standard.exception.BizException;

@Component
public class PresellProductBOImpl extends PaginableBOImpl<PresellProduct>
        implements IPresellProductBO {

    @Autowired
    private IPresellProductDAO presellProductDAO;

    @Override
    public PresellProduct savePresellProduct(XN629400Req req) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.PRESELL_PRODUCT.getCode());

        PresellProduct data = new PresellProduct();
        data.setCode(code);
        data.setName(req.getName());
        data.setOwnerId(req.getOwnerId());
        data.setParentCategoryCode(req.getParentCategoryCode());
        data.setCategoryCode(req.getCategoryCode());
        data.setListPic(req.getListPic());

        data.setBannerPic(req.getBannerPic());
        data.setDescription(req.getDescription());
        data.setOriginPlace(req.getOriginPlace());
        data.setScientificName(req.getScientificName());
        data.setVariety(req.getVariety());

        data.setRank(req.getRank());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setTown(req.getTown());

        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());
        data.setPackUnit(req.getPackUnit());

        data.setNowCount(0);
        data.setStatus(EPresellProductStatus.DRAFT.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        Date adoptStartDatetime = DateUtil.strToDate(
            req.getAdoptStartDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING);
        Date adoptEndDatetime = DateUtil
            .getEndDatetime(req.getAdoptEndDatetime());

        data.setAdoptStartDatetime(adoptStartDatetime);
        data.setAdoptEndDatetime(adoptEndDatetime);
        data.setAdoptYear(
            DateUtil.yearsBetween(adoptStartDatetime, adoptEndDatetime));
        data.setHarvestDatetime(DateUtil.strToDate(req.getHarvestDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setDeliverDatetime(DateUtil.strToDate(req.getDeliverDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));

        Integer treeCode = req.getTreeList().size();
        Integer singOutput = StringValidater.toInteger(req.getSingleOutput());
        Integer totalOutput = treeCode * singOutput;

        data.setTreeCount(treeCode);
        data.setSingleOutput(singOutput);
        data.setTotalOutput(totalOutput);

        presellProductDAO.insert(data);
        return data;
    }

    @Override
    public void refreshEditPresellProduct(XN629401Req req) {
        PresellProduct data = new PresellProduct();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setParentCategoryCode(req.getParentCategoryCode());
        data.setCategoryCode(req.getCategoryCode());
        data.setListPic(req.getListPic());

        data.setBannerPic(req.getBannerPic());
        data.setDescription(req.getDescription());
        data.setOriginPlace(req.getOriginPlace());
        data.setScientificName(req.getScientificName());
        data.setVariety(req.getVariety());

        data.setRank(req.getRank());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setTown(req.getTown());

        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());
        data.setPackUnit(req.getPackUnit());

        data.setAdoptStartDatetime(DateUtil.strToDate(
            req.getAdoptStartDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setAdoptEndDatetime(
            DateUtil.getEndDatetime(req.getAdoptEndDatetime()));
        data.setHarvestDatetime(DateUtil.strToDate(req.getHarvestDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setDeliverDatetime(DateUtil.strToDate(req.getDeliverDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        Integer treeCode = req.getTreeList().size();
        Integer singOutput = StringValidater.toInteger(req.getSingleOutput());
        Integer totalOutput = treeCode * singOutput;

        data.setTreeCount(treeCode);
        data.setSingleOutput(singOutput);
        data.setTotalOutput(totalOutput);

        presellProductDAO.updateEditPresellProduct(data);

    }

    @Override
    public void refreshSubmitPresellProduct(String code, String updater,
            String remark) {
        PresellProduct presellProduct = new PresellProduct();
        presellProduct.setCode(code);
        presellProduct.setStatus(EPresellProductStatus.TO_APPROVE.getCode());
        presellProduct.setUpdater(updater);
        presellProduct.setUpdateDatetime(new Date());
        presellProductDAO.updateSubmitPresellProduct(presellProduct);
    }

    @Override
    public void refreshApprovePresellProduct(String code, String status,
            String updater, String remark) {
        PresellProduct presellProduct = new PresellProduct();
        presellProduct.setCode(code);
        presellProduct.setStatus(status);
        presellProduct.setRemark(remark);
        presellProduct.setUpdater(updater);
        presellProduct.setUpdateDatetime(new Date());
        presellProductDAO.updateApprovePresellProduct(presellProduct);
    }

    @Override
    public void refreshPutOnPresellProduct(String code, String location,
            Integer orderNo, String updater) {
        PresellProduct presellProduct = new PresellProduct();
        presellProduct.setCode(code);
        presellProduct.setStatus(EPresellProductStatus.TO_ADOPT.getCode());
        presellProduct.setLocation(location);
        presellProduct.setOrderNo(orderNo);

        presellProduct.setUpdater(updater);
        presellProduct.setUpdateDatetime(new Date());
        presellProductDAO.updatePutOnPresellProduct(presellProduct);
    }

    @Override
    public void refreshPutOffPresellProduct(String code, String updater) {
        PresellProduct presellProduct = new PresellProduct();
        presellProduct.setCode(code);
        presellProduct.setStatus(EPresellProductStatus.PUTOFFED.getCode());
        presellProduct.setUpdater(updater);
        presellProduct.setUpdateDatetime(new Date());
        presellProductDAO.updatePutOffPresellProduct(presellProduct);
    }

    @Override
    public void refreshNowCount(String code, Integer nowCount) {
        PresellProduct presellProduct = new PresellProduct();
        presellProduct.setCode(code);
        presellProduct.setNowCount(nowCount);
        presellProductDAO.updateNowCount(presellProduct);
    }

    @Override
    public List<PresellProduct> queryPresellProductList(
            PresellProduct condition) {
        return presellProductDAO.selectList(condition);
    }

    @Override
    public PresellProduct getPresellProduct(String code) {
        PresellProduct data = null;
        if (StringUtils.isNotBlank(code)) {
            PresellProduct condition = new PresellProduct();
            condition.setCode(code);
            data = presellProductDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "预售产品不存在");
            }
        }
        return data;
    }

}
