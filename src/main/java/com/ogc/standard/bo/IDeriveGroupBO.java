package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.DeriveGroup;

public interface IDeriveGroupBO extends IPaginableBO<DeriveGroup> {

    public String saveDeriveGroup(DeriveGroup data);

    public List<DeriveGroup> queryDeriveGroupList(DeriveGroup condition);

    public DeriveGroup getDeriveGroup(String code);

}
