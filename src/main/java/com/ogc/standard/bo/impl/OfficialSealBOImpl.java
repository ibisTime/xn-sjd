package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IOfficialSealBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IOfficialSealDAO;
import com.ogc.standard.domain.OfficialSeal;
import com.ogc.standard.dto.req.XN629670Req;
import com.ogc.standard.dto.req.XN629672Req;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class OfficialSealBOImpl extends PaginableBOImpl<OfficialSeal>
        implements IOfficialSealBO {

    @Autowired
    private IOfficialSealDAO officialSealDAO;

    @Override
    public String saveOfficialSeal(XN629670Req req) {

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OFFICIAL_SEAL.getCode());

        OfficialSeal officialSeal = new OfficialSeal();

        officialSeal.setCode(code);
        officialSeal.setProvince(req.getProvince());
        officialSeal.setCity(req.getCity());
        officialSeal.setArea(req.getArea());
        officialSeal.setDepartment(req.getDepartment());

        officialSeal.setPic(req.getPic());
        officialSeal.setUpdater(req.getUpdater());
        officialSeal.setUpdateDatetime(new Date());
        officialSeal.setRemark(req.getRemark());

        officialSealDAO.insert(officialSeal);
        return code;

    }

    @Override
    public void removeOfficialSeal(String code) {
        OfficialSeal data = new OfficialSeal();
        data.setCode(code);
        officialSealDAO.delete(data);
    }

    @Override
    public void refreshOfficialSeal(XN629672Req req) {
        OfficialSeal officialSeal = new OfficialSeal();

        officialSeal.setCode(req.getCode());
        officialSeal.setProvince(req.getProvince());
        officialSeal.setCity(req.getCity());
        officialSeal.setArea(req.getArea());
        officialSeal.setDepartment(req.getDepartment());

        officialSeal.setPic(req.getPic());
        officialSeal.setUpdater(req.getUpdater());
        officialSeal.setUpdateDatetime(new Date());
        officialSeal.setRemark(req.getRemark());

        officialSealDAO.updateOfficialSeal(officialSeal);
    }

    @Override
    public List<OfficialSeal> queryOfficialSealList(OfficialSeal condition) {
        return officialSealDAO.selectList(condition);
    }

    @Override
    public List<OfficialSeal> queryOfficialSealList(String province, String city,
            String area, String department) {
        OfficialSeal data = new OfficialSeal();

        data.setProvince(province);
        data.setCity(city);
        data.setArea(area);
        data.setDepartment(department);

        return officialSealDAO.selectList(data);
    }

    @Override
    public OfficialSeal getOfficialSeal(String code) {
        OfficialSeal data = null;
        if (StringUtils.isNotBlank(code)) {
            OfficialSeal condition = new OfficialSeal();
            condition.setCode(code);
            data = officialSealDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "公章不存在");
            }
        }
        return data;
    }

}
