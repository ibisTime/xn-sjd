package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IOfficialSealBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IAdoptOrderTreeDAO;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.GroupOrder;
import com.ogc.standard.domain.OfficialSeal;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.EAdoptOrderTreeType;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class AdoptOrderTreeBOImpl extends PaginableBOImpl<AdoptOrderTree>
        implements IAdoptOrderTreeBO {

    @Autowired
    private IAdoptOrderTreeDAO adoptOrderTreeDAO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOfficialSealBO officialSealBO;

    @Override
    public String saveAdoptOrderTree(Product product, AdoptOrder adoptOrder,
            String treeNumber) {
        AdoptOrderTree data = new AdoptOrderTree();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ADOPT_ORDER_TREE.getCode());
        data.setCode(code);
        data.setOrderType(adoptOrder.getType());
        data.setOrderCode(adoptOrder.getCode());
        data.setParentCategoryCode(product.getParentCategoryCode());
        data.setCategoryCode(product.getCategoryCode());

        data.setOwnerId(product.getOwnerId());
        data.setProductCode(product.getCode());
        data.setTreeNumber(treeNumber);
        data.setStartDatetime(adoptOrder.getStartDatetime());
        data.setEndDatetime(adoptOrder.getEndDatetime());

        data.setAmount(adoptOrder.getPrice());
        data.setQuantity(1);
        data.setCreateDatetime(new Date());
        if (EAdoptOrderStatus.TO_ADOPT.getCode()
            .equals(adoptOrder.getStatus())) {
            data.setStatus(EAdoptOrderTreeStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
        }

        Company company = companyBO.getCompanyByUserId(product.getOwnerId());
        data.setCertificateTemplate(company.getCertificateTemplate());
        data.setCurrentHolder(adoptOrder.getApplyUser());

        String contract = contractGenerator(code, product, adoptOrder, company);

        data.setContract(contract);
        adoptOrderTreeDAO.insert(data);
        return code;
    }

    @Override
    public String saveAdoptOrderTree(Product product,
            GroupAdoptOrder groupAdoptOrder, String treeNumber) {
        AdoptOrderTree data = new AdoptOrderTree();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ADOPT_ORDER_TREE.getCode());
        data.setCode(code);
        data.setOrderType(EAdoptOrderTreeType.COLLECTIVE.getCode());
        data.setOrderCode(groupAdoptOrder.getCode());
        data.setParentCategoryCode(product.getParentCategoryCode());
        data.setCategoryCode(product.getCategoryCode());

        data.setOwnerId(product.getOwnerId());
        data.setProductCode(product.getCode());
        data.setTreeNumber(treeNumber);
        data.setStartDatetime(groupAdoptOrder.getStartDatetime());
        data.setEndDatetime(groupAdoptOrder.getEndDatetime());

        data.setAmount(groupAdoptOrder.getAmount());
        data.setQuantity(groupAdoptOrder.getQuantity());
        data.setCreateDatetime(new Date());
        if (EAdoptOrderStatus.TO_ADOPT.getCode()
            .equals(groupAdoptOrder.getStatus())) {
            data.setStatus(EAdoptOrderTreeStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
        }

        Company company = companyBO.getCompanyByUserId(product.getOwnerId());
        data.setCertificateTemplate(company.getCertificateTemplate());
        data.setCurrentHolder(groupAdoptOrder.getApplyUser());

        String contract = contractGenerator(code, product, groupAdoptOrder,
            company);

        data.setContract(contract);
        adoptOrderTreeDAO.insert(data);
        return code;
    }

    @Override
    public String saveAdoptOrderTree(PresellProduct presellProduct,
            PresellOrder presellOrder, String treeNumber) {
        AdoptOrderTree data = new AdoptOrderTree();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ADOPT_ORDER_TREE.getCode());
        data.setCode(code);
        data.setOrderType(EAdoptOrderTreeType.PRESELL.getCode());
        data.setOrderCode(presellOrder.getCode());
        data.setParentCategoryCode(presellProduct.getParentCategoryCode());
        data.setCategoryCode(presellProduct.getCategoryCode());

        data.setOwnerId(presellProduct.getOwnerId());
        data.setProductCode(presellProduct.getCode());
        data.setTreeNumber(treeNumber);
        data.setStartDatetime(presellProduct.getAdoptStartDatetime());
        data.setEndDatetime(presellProduct.getAdoptEndDatetime());

        data.setAmount(presellOrder.getPrice());
        data.setQuantity(1);
        data.setCreateDatetime(new Date());
        if ((new Date()).before(presellProduct.getAdoptStartDatetime())) {
            data.setStatus(EAdoptOrderTreeStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
        }

        Company company = companyBO
            .getCompanyByUserId(presellProduct.getOwnerId());
        data.setCertificateTemplate(company.getCertificateTemplate());
        data.setCurrentHolder(presellOrder.getApplyUser());
        adoptOrderTreeDAO.insert(data);
        return code;
    }

    @Override
    public String saveAdoptOrderTree(PresellProduct presellProduct,
            GroupOrder groupOrder, String treeNumber) {
        AdoptOrderTree data = new AdoptOrderTree();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ADOPT_ORDER_TREE.getCode());
        data.setCode(code);
        data.setOrderType(EAdoptOrderTreeType.CONSIGN.getCode());
        data.setOrderCode(groupOrder.getCode());
        data.setParentCategoryCode(presellProduct.getParentCategoryCode());
        data.setCategoryCode(presellProduct.getCategoryCode());

        data.setOwnerId(presellProduct.getOwnerId());
        data.setProductCode(presellProduct.getCode());
        data.setTreeNumber(treeNumber);
        data.setStartDatetime(presellProduct.getAdoptStartDatetime());
        data.setEndDatetime(presellProduct.getAdoptEndDatetime());

        data.setAmount(groupOrder.getPrice());
        data.setQuantity(1);
        data.setCreateDatetime(new Date());
        if ((new Date()).before(presellProduct.getAdoptStartDatetime())) {
            data.setStatus(EAdoptOrderTreeStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
        }

        Company company = companyBO
            .getCompanyByUserId(presellProduct.getOwnerId());
        data.setCertificateTemplate(company.getCertificateTemplate());
        data.setCurrentHolder(groupOrder.getApplyUser());
        adoptOrderTreeDAO.insert(data);
        return code;
    }

    @Override
    public void giveTree(AdoptOrderTree data, User user, User toUser) {
        // 终止现有的认养权
        data.setStatus(EAdoptOrderTreeStatus.PRESENT.getCode());
        data.setRemark("赠送给" + toUser.getMobile());
        adoptOrderTreeDAO.updateStatus(data);

        // 产生新的认养权
        AdoptOrderTree newData = new AdoptOrderTree();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ADOPT_ORDER_TREE.getCode());
        newData.setCode(code);
        newData.setOrderType(EAdoptOrderTreeType.GIVE.getCode());
        newData.setOrderCode(data.getOrderCode());
        newData.setParentCategoryCode(data.getParentCategoryCode());
        newData.setCategoryCode(data.getCategoryCode());
        newData.setProductCode(data.getProductCode());

        newData.setTreeNumber(data.getTreeNumber());
        newData.setOwnerId(data.getOwnerId());
        newData.setStartDatetime(new Date());
        newData.setEndDatetime(data.getEndDatetime());
        newData.setAmount(data.getAmount());
        newData.setQuantity(data.getQuantity());

        newData.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
        newData.setCertificateTemplate(data.getCertificateTemplate());
        newData.setCurrentHolder(toUser.getUserId());
        newData.setCreateDatetime(new Date());
        newData.setRemark(user.getMobile() + "赠送");

        adoptOrderTreeDAO.insert(newData);
    }

    // 生成协议
    private String contractGenerator(String code, Product product,
            AdoptOrder adoptOrder, Company company) {
        String contract = company.getContractTemplate();// 合同
        String firstName = company.getName();// 甲方名称
        String secondName = userBO.getUser(adoptOrder.getApplyUser())
            .getRealName();// 乙方

        String thirdName = "";// 丙方
        String thirdOfficialSeal = "";// 丙方公章
        List<OfficialSeal> officialSeals = officialSealBO.queryOfficialSealList(
            product.getProvince(), product.getCity(), product.getArea(), null);
        if (CollectionUtils.isNotEmpty(officialSeals)) {
            thirdName = officialSeals.get(0).getDepartment();
            thirdOfficialSeal = StringUtils
                .isBlank(officialSeals.get(0).getPic())
                        ? null
                        : "<img src=\"http://image.o2lin.com/"
                                + company.getCommonSeal()
                                + "?imageMogr2/auto-orient\">";
        }

        String startDateYear = DateUtil.dateToStr(adoptOrder.getStartDatetime(),
            "yyyy");// 开始时间年份
        String startDateMonth = DateUtil
            .dateToStr(adoptOrder.getStartDatetime(), "MM");// 开始时间月份
        String startDateDay = DateUtil.dateToStr(adoptOrder.getStartDatetime(),
            "dd");// 开始时间日期

        String endDateYear = DateUtil.dateToStr(adoptOrder.getEndDatetime(),
            "yyyy");// 结束时间年份
        String endDateMonth = DateUtil.dateToStr(adoptOrder.getEndDatetime(),
            "MM");// 结束时间月份
        String endDateDay = DateUtil.dateToStr(adoptOrder.getEndDatetime(),
            "dd");// 结束时间日期

        String price = AmountUtil.div(adoptOrder.getPrice(), 1000L).toString();
        String signDate = DateUtil.dateToStr(new Date(),
            DateUtil.DATA_TIME_PATTERN_9);// 签约日期
        String firstOfficialSeal = StringUtils.isBlank(company.getCommonSeal())
                ? null
                : "<img src=\"http://image.o2lin.com/" + company.getCommonSeal()
                        + "?imageMogr2/auto-orient\">";// 甲方公章

        contract = contract.replace("##甲方名称##", firstName)
            .replace("##乙方名称##", secondName).replace("##丙方名称##", thirdName)
            .replace("##认养编号##", code).replace("##quantity##", "1")
            .replace("##y1##", startDateYear).replace("##m1##", startDateMonth)
            .replace("##d1##", startDateDay).replace("##y2##", endDateYear)
            .replace("##m2##", endDateMonth).replace("##d2##", endDateDay)
            .replace("##price##", price).replace("##date1##", signDate)
            .replace("##date2##", signDate).replace("##date3##", signDate)
            .replace("##cachet1##", "< img src=" + firstOfficialSeal + ">")
            .replace("##cachet3##", "< img src=" + thirdOfficialSeal + ">");

        return contract;
    }

    // 生成协议
    private String contractGenerator(String code, Product product,
            GroupAdoptOrder groupAdoptOrder, Company company) {
        String contract = company.getContractTemplate();// 合同
        String firstName = company.getName();// 甲方名称
        String secondName = userBO.getUser(groupAdoptOrder.getApplyUser())
            .getRealName();// 乙方

        String thirdName = "";// 丙方
        String thirdOfficialSeal = "";// 丙方公章
        List<OfficialSeal> officialSeals = officialSealBO.queryOfficialSealList(
            product.getProvince(), product.getCity(), product.getArea(), null);
        if (CollectionUtils.isNotEmpty(officialSeals)) {
            thirdName = officialSeals.get(0).getDepartment();
            thirdOfficialSeal = StringUtils
                .isBlank(officialSeals.get(0).getPic())
                        ? null
                        : "<img src=\"http://image.o2lin.com/"
                                + company.getCommonSeal()
                                + "?imageMogr2/auto-orient\">";
        }

        String startDateYear = DateUtil
            .dateToStr(groupAdoptOrder.getStartDatetime(), "yyyy");// 开始时间年份
        String startDateMonth = DateUtil
            .dateToStr(groupAdoptOrder.getStartDatetime(), "MM");// 开始时间月份
        String startDateDay = DateUtil
            .dateToStr(groupAdoptOrder.getStartDatetime(), "dd");// 开始时间日期

        String endDateYear = DateUtil
            .dateToStr(groupAdoptOrder.getEndDatetime(), "yyyy");// 结束时间年份
        String endDateMonth = DateUtil
            .dateToStr(groupAdoptOrder.getEndDatetime(), "MM");// 结束时间月份
        String endDateDay = DateUtil.dateToStr(groupAdoptOrder.getEndDatetime(),
            "dd");// 结束时间日期

        String price = AmountUtil.div(groupAdoptOrder.getPrice(), 1000L)
            .toString();
        String signDate = DateUtil.dateToStr(new Date(),
            DateUtil.DATA_TIME_PATTERN_9);// 签约日期
        String firstOfficialSeal = StringUtils.isBlank(company.getCommonSeal())
                ? null
                : "<img src=\"http://image.o2lin.com/" + company.getCommonSeal()
                        + "?imageMogr2/auto-orient\">";// 甲方公章

        contract = contract.replace("##甲方名称##", firstName)
            .replace("##乙方名称##", secondName).replace("##丙方名称##", thirdName)
            .replace("##认养编号##", code).replace("##quantity##", "1")
            .replace("##y1##", startDateYear).replace("##m1##", startDateMonth)
            .replace("##d1##", startDateDay).replace("##y2##", endDateYear)
            .replace("##m2##", endDateMonth).replace("##d2##", endDateDay)
            .replace("##price##", price).replace("##date1##", signDate)
            .replace("##date2##", signDate).replace("##date3##", signDate)
            .replace("##cachet1##", "< img src=" + firstOfficialSeal + ">")
            .replace("##cachet3##", "< img src=" + thirdOfficialSeal + ">");

        return contract;
    }

    @Override
    public void refreshAdoptOrderTree(AdoptOrderTree data,
            EAdoptOrderTreeStatus adoptOrderTreeStatus) {
        if (data != null) {
            data.setStatus(adoptOrderTreeStatus.getCode());
            adoptOrderTreeDAO.updateStatus(data);
        }
    }

    @Override
    public void refreshQuantity(String code, Integer quantity) {
        AdoptOrderTree data = new AdoptOrderTree();
        data.setCode(code);
        data.setQuantity(quantity);
        adoptOrderTreeDAO.updateQuantity(data);
    }

    @Override
    public long getCountByCurrentHolder(String currentHolder) {
        long count = 0;

        if (StringUtils.isNotBlank(currentHolder)) {
            AdoptOrderTree condition = new AdoptOrderTree();
            condition.setCurrentHolder(currentHolder);
            count = adoptOrderTreeDAO.selectTotalCount(condition);
        }

        return count;
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(
            AdoptOrderTree condition) {
        return adoptOrderTreeDAO.selectList(condition);
    }

    @Override
    public List<AdoptOrderTree> queryUserAdoptedOrder(String userId,
            String treeNumber) {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setCurrentHolder(userId);
        condition.setTreeNumber(treeNumber);

        List<String> statusList = new ArrayList<String>();
        statusList.add(EAdoptOrderTreeStatus.TO_ADOPT.getCode());
        statusList.add(EAdoptOrderTreeStatus.ADOPT.getCode());
        condition.setStatusList(statusList);
        return queryAdoptOrderTreeList(condition);
    }

    @Override
    public List<AdoptOrderTree> queryProductAdoptedOrder(
            AdoptOrderTree condition) {
        return adoptOrderTreeDAO.selectProductAdoptedOrder(condition);
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(String orderCode) {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setOrderCode(orderCode);
        return adoptOrderTreeDAO.selectList(condition);
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(String orderCode,
            String status) {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setOrderCode(orderCode);
        condition.setStatus(status);
        return adoptOrderTreeDAO.selectList(condition);
    }

    @Override
    public AdoptOrderTree getAdoptOrderTree(String code) {
        AdoptOrderTree data = null;
        if (StringUtils.isNotBlank(code)) {
            AdoptOrderTree condition = new AdoptOrderTree();
            condition.setCode(code);
            data = adoptOrderTreeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "认养权不存在");
            }
        }
        return data;
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeByNum(String treeNumber) {
        List<AdoptOrderTree> list = new ArrayList<AdoptOrderTree>();
        if (StringUtils.isNotBlank(treeNumber)) {
            AdoptOrderTree condition = new AdoptOrderTree();
            condition.setTreeNumber(treeNumber);
            condition.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
            list = adoptOrderTreeDAO.selectList(condition);

            if (CollectionUtils.isNotEmpty(list)) {
                for (AdoptOrderTree adoptOrderTree : list) {
                    init(adoptOrderTree);
                }
            }
        }
        return list;

    }

    @Override
    public List<AdoptOrderTree> queryDistictByTreeNumber(String treeNumber) {
        List<AdoptOrderTree> list = new ArrayList<AdoptOrderTree>();
        if (StringUtils.isNotBlank(treeNumber)) {
            AdoptOrderTree condition = new AdoptOrderTree();
            condition.setTreeNumber(treeNumber);
            condition.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
            list = adoptOrderTreeDAO.selectDistictByTreeNumber(condition);

            if (CollectionUtils.isNotEmpty(list)) {
                for (AdoptOrderTree adoptOrderTree : list) {

                    // 当前持有人信息
                    User user = userBO
                        .getUserUnCheck(adoptOrderTree.getCurrentHolder());
                    if (null != user) {
                        adoptOrderTree.setUser(user);
                    }

                }
            }
        }
        return list;
    }

    @Override
    public List<AdoptOrderTree> queryAdoptedOrderTreeByUser(String userId,
            String status) {
        List<AdoptOrderTree> list = new ArrayList<AdoptOrderTree>();

        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setCurrentHolder(userId);
        condition.setStatus(status);

        list = adoptOrderTreeDAO.selectList(condition);

        return list;
    }

    private void init(AdoptOrderTree data) {
        // 树木信息
        Tree tree = treeBO.getTreeByTreeNumber(data.getTreeNumber());
        data.setTree(tree);

        // 当前持有人信息
        User user = userBO.getUserUnCheck(data.getCurrentHolder());
        if (null != user) {
            data.setUser(user);
        }
    }

    @Override
    public long getCountByOwner(String ownerId, Date createDatetimeStart,
            Date createDatetimeEnd) {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setOwnerId(ownerId);
        condition.setCreateDatetimeStart(createDatetimeStart);
        condition.setCreateDatetimeEnd(createDatetimeEnd);
        return adoptOrderTreeDAO.selectTotalCount(condition);
    }

    @Override
    public long getDistinctCountByOwner(String ownerId,
            Date createDatetimeStart, Date createDatetimeEnd,
            List<String> orderTypeList) {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setOwnerId(ownerId);
        condition.setCreateDatetimeStart(createDatetimeStart);
        condition.setCreateDatetimeEnd(createDatetimeEnd);
        condition.setOrderTypeList(orderTypeList);
        return adoptOrderTreeDAO.selectDistinctTotalCount(condition);
    }

    @Override
    public List<AdoptOrderTree> getDistictVariety() {
        return adoptOrderTreeDAO.selectDistictVariety(new AdoptOrderTree());
    }

    @Override
    public BigDecimal getTotalAmount(String ownerId, List<String> statusList,
            List<String> orderTypeList) {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setOwnerId(ownerId);
        condition.setStatusList(statusList);
        condition.setOrderTypeList(orderTypeList);
        return adoptOrderTreeDAO.selectTotalAmount(condition);
    }

}
