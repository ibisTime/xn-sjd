package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Visitor;

public interface IVisitorAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addVisitor(Visitor data);

    public int dropVisitor(String code);

    public int editVisitor(Visitor data);

    public Paginable<Visitor> queryVisitorPage(int start, int limit,
            Visitor condition);

    public List<Visitor> queryVisitorList(Visitor condition);

    public Visitor getVisitor(String code);

}
