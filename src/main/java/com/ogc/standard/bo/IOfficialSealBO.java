package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.OfficialSeal;
import com.ogc.standard.dto.req.XN629670Req;
import com.ogc.standard.dto.req.XN629672Req;

public interface IOfficialSealBO extends IPaginableBO<OfficialSeal> {

    public String saveOfficialSeal(XN629670Req req);

    public void removeOfficialSeal(String code);

    public void refreshOfficialSeal(XN629672Req req);

    public List<OfficialSeal> queryOfficialSealList(OfficialSeal condition);

    public List<OfficialSeal> queryOfficialSealList(String province, String city,
            String area, String department);

    public OfficialSeal getOfficialSeal(String code);

}
