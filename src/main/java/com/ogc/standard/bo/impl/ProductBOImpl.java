package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICategoryBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dao.IProductDAO;
import com.ogc.standard.domain.Category;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.dto.req.XN629010Req;
import com.ogc.standard.dto.req.XN629011Req;
import com.ogc.standard.dto.res.XN630065PriceRes;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EProductStatus;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Component
public class ProductBOImpl extends PaginableBOImpl<Product>
        implements IProductBO {

    @Autowired
    private IProductDAO productDAO;

    @Autowired
    private ICategoryBO categoryBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public Product saveProduct(XN629010Req req) {
        if (ESellType.DONATE.getCode().equals(req.getSellType())) {
            if (req.getRaiseStartDatetime()
                .compareTo(req.getRaiseEndDatetime()) >= 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "募集结束时间需大于募集开始时间");
            }
        }

        if (ESellType.COLLECTIVE.getCode().equals(req.getSellType())) {
            if (StringUtils.isBlank(req.getRaiseCount())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "募集总数量不能为空");
            }
        }

        Product data = new Product();

        Category category = categoryBO.getCategory(req.getCategoryCode());
        if (StringUtils.isNotBlank(category.getParentCode())) {
            Category parentCategory = categoryBO
                .getCategory(category.getParentCode());
            data.setParentCategoryCode(parentCategory.getCode());
        } else {
            data.setParentCategoryCode(req.getCategoryCode());
        }

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Product.getCode());
        data.setCode(code);
        data.setName(req.getName());
        data.setSellType(req.getSellType());
        data.setDirectType(req.getDirectType());
        data.setDirectObject(req.getDirectObject());
        data.setCategoryCode(req.getCategoryCode());
        data.setOwnerId(req.getOwnerId());

        data.setListPic(req.getListPic());
        data.setBannerPic(req.getBannerPic());
        data.setOriginPlace(req.getOriginPlace());
        data.setScientificName(req.getScientificName());
        data.setVariety(req.getVariety());

        data.setRank(req.getRank());
        data.setDescription(req.getDescription());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setTown(req.getTown());

        data.setRaiseStartDatetime(DateUtil.strToDate(
            req.getRaiseStartDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setRaiseEndDatetime(DateUtil.strToDate(req.getRaiseEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setRaiseCount(StringValidater.toInteger(req.getRaiseCount()));
        data.setNowCount(0);
        data.setStatus(EProductStatus.DRAFT.getCode());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        productDAO.insert(data);
        return data;
    }

    @Override
    public void refreshProduct(Product data, XN629011Req req) {
        data.setName(req.getName());
        data.setSellType(req.getSellType());
        data.setDirectType(req.getDirectType());
        data.setDirectObject(req.getDirectObject());
        data.setCategoryCode(req.getCategoryCode());
        data.setOwnerId(req.getOwnerId());
        data.setListPic(req.getListPic());

        data.setBannerPic(req.getBannerPic());
        data.setOriginPlace(req.getOriginPlace());
        data.setScientificName(req.getScientificName());
        data.setVariety(req.getVariety());
        data.setRank(req.getRank());

        data.setDescription(req.getDescription());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setTown(req.getTown());

        if (ESellType.COLLECTIVE.getCode().equals(req.getSellType())) {
            if (req.getRaiseStartDatetime()
                .compareTo(req.getRaiseEndDatetime()) >= 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "募集结束时间需大于募集开始时间");
            }
            if (StringUtils.isBlank(req.getRaiseCount())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "募集总数量不能为空");
            }
        }
        data.setRaiseStartDatetime(DateUtil.strToDate(
            req.getRaiseStartDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setRaiseEndDatetime(DateUtil.strToDate(req.getRaiseEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setRaiseCount(StringValidater.toInteger(req.getRaiseCount()));

        if (EProductStatus.APPROVE_NO.getCode().equals(data.getStatus())) {
            data.setStatus(EProductStatus.DRAFT.getCode());
        }
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        productDAO.updateProduct(data);
    }

    @Override
    public void refreshSubmitProduct(String code, String updater,
            String remark) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.TO_APPROVE.getCode());
        product.setUpdater(updater);
        product.setUpdateDatetime(new Date());
        product.setRemark(remark);

        productDAO.updateSubmitProduct(product);
    }

    @Override
    public void refreshApproveProduct(String code, String status,
            String updater, String remark) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(status);
        product.setUpdater(updater);
        product.setRemark(remark);
        product.setUpdateDatetime(new Date());

        productDAO.updateApproveProduct(product);
    }

    @Override
    public void refreshPutOnProduct(String code, String location,
            Integer orderNo, String updater, String remark) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.TO_ADOPT.getCode());
        product.setLocation(location);
        product.setOrderNo(orderNo);
        product.setUpdater(updater);

        product.setRemark(remark);
        product.setUpdateDatetime(new Date());
        productDAO.updatePutOnProduct(product);
    }

    @Override
    public void refreshLoakProduct(String code) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.LOCKED.getCode());

        productDAO.updateLockProduct(product);
    }

    @Override
    public void refreshRaiseCount(String code, Integer raiseCount) {
        Product product = new Product();

        product.setCode(code);
        product.setRaiseCount(raiseCount);

        productDAO.updateRaiseCount(product);
    }

    @Override
    public void refreshNowCount(String code, Integer nowCount) {
        Product product = new Product();

        product.setCode(code);
        product.setNowCount(nowCount);

        productDAO.updateNowCount(product);
    }

    @Override
    public void refreshAdoptProduct(String code) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.ADOPT.getCode());

        productDAO.updateAdoptProduct(product);
    }

    @Override
    public void refreshPutOffProduct(String code, String updater) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.PUTOFFED.getCode());
        product.setUpdater(updater);
        product.setUpdateDatetime(new Date());

        productDAO.updatePutOffProduct(product);
    }

    @Override
    public void refreshCurrentIdentify(String code, String identifyCode) {
        Map<String, String> mapList = sysConfigBO.getConfigsMap();
        Integer idInvalidHours = Integer
            .valueOf(mapList.get(SysConstants.ID_INVALID_HOURS));// 识别码失效时间
        Date idInvalidDatetime = DateUtil.getRelativeDateOfHour(new Date(),
            idInvalidHours);

        Product product = new Product();
        product.setCode(code);
        product.setIdentifyCode(identifyCode);
        product.setIdInvalidDatetime(idInvalidDatetime);

        productDAO.updateCurrentIdentify(product);

    }

    @Override
    public XN630065PriceRes getOwnerProductPrice(String ownerId) {
        Product productCondition = new Product();
        productCondition.setOwnerId(ownerId);
        List<Product> productList = queryProductList(productCondition);

        // 初始化最小价格和最大价格
        BigDecimal minPrice = BigDecimal.ZERO;
        BigDecimal maxPrice = minPrice;

        if (CollectionUtils.isNotEmpty(productList)) {
            for (Product product : productList) {
                List<ProductSpecs> specsList = productSpecsBO
                    .queryProductSpecsListByProduct(product.getCode());

                BigDecimal minPriceTmp = BigDecimal.ZERO;
                BigDecimal maxPriceTmp = minPriceTmp;
                for (ProductSpecs productSpecs : specsList) {
                    if (minPriceTmp.compareTo(BigDecimal.ZERO) == 0) {
                        minPriceTmp = productSpecs.getPrice();
                    }
                    if (productSpecs.getPrice().compareTo(minPriceTmp) < 0) {
                        minPriceTmp = productSpecs.getPrice();
                    }
                    if (productSpecs.getPrice().compareTo(maxPriceTmp) > 0) {
                        maxPriceTmp = productSpecs.getPrice();
                    }
                }

                minPrice = minPrice
                    .add(AmountUtil.mul(minPriceTmp, product.getRaiseCount()));
                maxPrice = maxPrice
                    .add(AmountUtil.mul(maxPriceTmp, product.getRaiseCount()));
            }
        }

        return new XN630065PriceRes(maxPrice, minPrice);
    }

    @Override
    public List<Product> queryProductList(Product condition) {
        return productDAO.selectList(condition);
    }

    @Override
    public Product getProduct(String code) {
        Product data = null;
        if (StringUtils.isNotBlank(code)) {
            Product condition = new Product();
            condition.setCode(code);
            data = productDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "认养产品不存在！");
            }
        }
        return data;
    }

}
