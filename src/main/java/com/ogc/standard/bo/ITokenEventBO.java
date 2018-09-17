package com.ogc.standard.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.domain.TokenEvent;
import com.ogc.standard.token.OrangeCoinToken.TransferEventResponse;

@Component
public interface ITokenEventBO {

    public void insertEventsList(List<TokenEvent> tokenEventList);

    public List<TokenEvent> queryListByHash(String hash);

    public void insertTokenEvent(TokenEvent tokenEvent);

    public long getTotalCount(TokenEvent condition);

    TokenEvent convertTokenEvent(TransferEventResponse transferEventResponse,
            String hash, String symbol);
}
