package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SignLog;

public interface ISignLogBO extends IPaginableBO<SignLog> {

    public boolean isSignLogExist(Integer id);

    public String saveSignLog(SignLog data);

    public List<SignLog> querySignLogList(SignLog condition);

}
