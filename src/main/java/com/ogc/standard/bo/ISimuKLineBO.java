package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SimuKLine;

public interface ISimuKLineBO extends IPaginableBO<SimuKLine> {

    public SimuKLine saveSimuKLine(SimuKLine data);

    public List<SimuKLine> querySimuKLineList(SimuKLine condition);

}
