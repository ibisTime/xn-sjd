/**
 * @Title CommodityAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午1:26:31 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICommodityAO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommoditySpecsBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommoditySpecs;
import com.ogc.standard.dto.req.XN629700Req;
import com.ogc.standard.dto.req.XN629701Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECommodityStatus;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午1:26:31 
 * @history:
 */
@Service
public class CommodityAOImpl implements ICommodityAO {
    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private ICommoditySpecsBO commoditySpecsBO;

    @Override
    @Transactional
    public String addDraft(XN629700Req req) {
        String code = null;
        // 落地商品数据
        code = commodityBO.saveCommodity(req);
        // 落地规格数据
        for (CommoditySpecs data : req.getSpecsList()) {
            data.setCommodityCode(code);
        }
        commoditySpecsBO.saveSpecsList(req.getSpecsList());
        return code;
    }

    @Override
    @Transactional
    public void editCommodity(XN629701Req req) {
        // 状态判断
        Commodity data = commodityBO.getCommodity(req.getCode());
        if (!ECommodityStatus.DRAFT.getCode().equals(data.getStatus())
                && !ECommodityStatus.FAILED.getCode().equals(data.getStatus())
                && !ECommodityStatus.OFF.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该商品处于无法修改的状态");
        }
        // 修改数据
        commodityBO.refreshCommodity(req);

        // 删除原规格
        commoditySpecsBO.removeSpecs(req.getCode());
        // 落地新规格数据
        for (CommoditySpecs specs : req.getSpecsList()) {
            specs.setCommodityCode(req.getCode());
        }
        commoditySpecsBO.saveSpecsList(req.getSpecsList());
    }

    @Override
    public void publishCommodity(String code, String updater, String remark) {
        Commodity data = commodityBO.getCommodity(code);
        if (!ECommodityStatus.DRAFT.getCode().equals(data.getStatus())
                && !ECommodityStatus.FAILED.getCode().equals(data.getStatus())
                && !ECommodityStatus.OFF.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该商品处于无法提交的状态");
        }
        commodityBO.refreshStatus(code, ECommodityStatus.TOAPPROVE.getCode(),
            updater, remark);
    }

    @Override
    public void approveCommodity(String code, String approver,
            String approveResult, String approveNote) {
        Commodity data = commodityBO.getCommodity(code);
        if (!ECommodityStatus.TOAPPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该商品处于无法审核的状态");
        }
        if (EBoolean.YES.getCode().equals(approveResult)) {
            commodityBO.refreshStatus(code, ECommodityStatus.PASS.getCode(),
                approver, approveNote);
        } else {
            commodityBO.refreshStatus(code, ECommodityStatus.FAILED.getCode(),
                approver, approveNote);
        }
    }

    @Override
    public void putOnShelf(String code, String location, Long orderNo,
            String updater, String remark) {
        Commodity data = commodityBO.getCommodity(code);
        if (!ECommodityStatus.PASS.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该商品处于无法上架的状态");
        }
        commodityBO.refreshOn(code, location, orderNo, updater, remark);
    }

    @Override
    public void obtained(String code, String updater, String remark) {
        Commodity data = commodityBO.getCommodity(code);
        if (!ECommodityStatus.ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该商品处于无法下架的状态");
        }
        commodityBO.refreshStatus(code, ECommodityStatus.OFF.getCode(),
            updater, remark);
    }

    @Override
    public Paginable<Commodity> queryCommodityPage(int start, int limit,
            Commodity condition) {
        Paginable<Commodity> page = commodityBO.getPaginable(start, limit,
            condition);
        for (Commodity data : page.getList()) {
            data.setSpecsList(commoditySpecsBO.querySpecsList(data.getCode()));
        }
        return page;
    }

    @Override
    public Commodity getCommodity(String code) {
        Commodity data = commodityBO.getCommodity(code);
        data.setSpecsList(commoditySpecsBO.querySpecsList(code));
        return data;
    }

    @Override
    public List<Commodity> queryCommodityList(Commodity condition) {
        List<Commodity> dataList = commodityBO.queryCommodityList(condition);
        for (Commodity commodity : dataList) {
            commodity.setSpecsList(commoditySpecsBO.querySpecsList(commodity
                .getCode()));
        }
        return dataList;
    }

}
