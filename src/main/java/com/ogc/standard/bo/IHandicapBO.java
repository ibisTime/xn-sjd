package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Handicap;

public interface IHandicapBO extends IPaginableBO<Handicap> {

    public void saveHandicap(Handicap data);

    public int removeHandicap(String code);

    public int refreshHandicap(Handicap data);

    public List<Handicap> queryHandicapList(Handicap condition);

    public List<Handicap> queryHandicapList(String symbol, String toSymbol,
            String direction, int handicapQuantity);

}
