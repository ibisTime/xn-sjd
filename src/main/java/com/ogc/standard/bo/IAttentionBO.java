package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Attention;

public interface IAttentionBO extends IPaginableBO<Attention> {

    public String saveAttention(String userId, String groupCode, String type);

    public int removeAttention(String code);

    public Attention getAttention(String userId, String groupCode, String type);

    // public String saveRemind(String userId, String groupCode);
    //
    // public int removeRemind(String code);
    //
    // public Attention getRemind(String userId, String groupCode);

}
