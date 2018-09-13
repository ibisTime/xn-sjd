package com.ogc.standard.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ITokenEventBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ITokenEventDAO;
import com.ogc.standard.domain.TokenEvent;

@Component
public class TokenEventBOImpl extends PaginableBOImpl<TokenEvent> implements
        ITokenEventBO {

    @Autowired
    private ITokenEventDAO tokenEventDAO;

    @Override
    public void insertEventsList(List<TokenEvent> tokenEventList) {
        tokenEventDAO.insertEventList(tokenEventList);
    }

    @Override
    public List<TokenEvent> queryListByHash(String hash) {
        TokenEvent condition = new TokenEvent();
        condition.setHash(hash);
        return tokenEventDAO.selectList(condition);
    }

    @Override
    public void insertTokenEvent(TokenEvent tokenEvent) {
        tokenEventDAO.insert(tokenEvent);
    }
}
