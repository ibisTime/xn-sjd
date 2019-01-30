package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.OfficialSeal;
import com.ogc.standard.dto.req.XN629670Req;
import com.ogc.standard.dto.req.XN629672Req;

@Component
public interface IOfficialSealAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addOfficialSeal(XN629670Req req);

    public void dropOfficialSeal(String code);

    public void editOfficialSeal(XN629672Req req);

    public Paginable<OfficialSeal> queryOfficialSealPage(int start, int limit,
            OfficialSeal condition);

    public List<OfficialSeal> queryOfficialSealList(OfficialSeal condition);

    public OfficialSeal getOfficialSeal(String code);

}
