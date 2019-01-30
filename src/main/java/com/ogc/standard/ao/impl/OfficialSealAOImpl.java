package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IOfficialSealAO;
import com.ogc.standard.bo.IOfficialSealBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.OfficialSeal;
import com.ogc.standard.dto.req.XN629670Req;
import com.ogc.standard.dto.req.XN629672Req;
import com.ogc.standard.exception.BizException;

@Service
public class OfficialSealAOImpl implements IOfficialSealAO {

    @Autowired
    private IOfficialSealBO officialSealBO;

    @Override
    public String addOfficialSeal(XN629670Req req) {
        if (CollectionUtils
            .isNotEmpty(officialSealBO.queryOfficialSealList(req.getProvince(),
                req.getCity(), req.getArea(), req.getDepartment()))) {
            throw new BizException("xn0000", "公章已存在，请勿重复添加");
        }

        return officialSealBO.saveOfficialSeal(req);
    }

    @Override
    public void dropOfficialSeal(String code) {
        officialSealBO.removeOfficialSeal(code);
    }

    @Override
    public void editOfficialSeal(XN629672Req req) {
        OfficialSeal officialSeal = officialSealBO
            .getOfficialSeal(req.getCode());

        if (!req.getProvince().equals(officialSeal.getProvince())
                || !req.getCity().equals(officialSeal.getCity())
                || !req.getArea().equals(officialSeal.getArea())
                || !req.getDepartment().equals(officialSeal.getDepartment())) {
            if (CollectionUtils.isNotEmpty(
                officialSealBO.queryOfficialSealList(req.getProvince(),
                    req.getCity(), req.getArea(), req.getDepartment()))) {
                throw new BizException("xn0000", "公章已存在，请勿重复添加");
            }
        }

        officialSealBO.refreshOfficialSeal(req);
    }

    @Override
    public Paginable<OfficialSeal> queryOfficialSealPage(int start, int limit,
            OfficialSeal condition) {
        return officialSealBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<OfficialSeal> queryOfficialSealList(OfficialSeal condition) {
        return officialSealBO.queryOfficialSealList(condition);
    }

    @Override
    public OfficialSeal getOfficialSeal(String code) {
        return officialSealBO.getOfficialSeal(code);
    }

}
