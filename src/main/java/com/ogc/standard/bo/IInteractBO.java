package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Interact;

/**
 * 点赞收藏表
 * @author: silver 
 * @since: 2018年8月22日 下午8:54:25 
 * @history:
 */
public interface IInteractBO extends IPaginableBO<Interact> {

    public boolean isInteractExist(String code);

    public String saveInteract(String type, String objectType,
            String objectCode, String userId);

    public List<Interact> queryInteractList(Interact condition);

    public Interact getInteract(String code);

}
