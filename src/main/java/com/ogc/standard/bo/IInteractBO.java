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

    // 添加点赞
    public String saveInteract(String type, String objectType,
            String objectCode, String userId);

    // 删除点赞
    public void removeInteract(String code);

    // 查询对象类型
    public Interact getInteract(String type, String objectType,
            String objectCode, String userId);

    // 查询用户点赞收藏
    public List<Interact> queryInteractList(String userId, String type,
            String objectType);

    public List<Interact> queryInteractList(Interact condition);

    public Interact getInteract(String code);

}
