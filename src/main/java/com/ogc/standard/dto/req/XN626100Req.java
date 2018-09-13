package com.ogc.standard.dto.req;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ogc.standard.domain.CtqBtcUtxo;

public class XN626100Req {

    @NotNull
    List<CtqBtcUtxo> utxoList;

    public List<CtqBtcUtxo> getUtxoList() {
        return utxoList;
    }

    public void setUtxoList(List<CtqBtcUtxo> utxoList) {
        this.utxoList = utxoList;
    }
}
