package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IAttentionAO;
import com.ogc.standard.bo.IAttentionBO;
import com.ogc.standard.bo.IGroupBO;
import com.ogc.standard.domain.Attention;
import com.ogc.standard.enums.EBoolean;

@Service
public class AttentionAOImpl implements IAttentionAO {

    @Autowired
    private IAttentionBO attentionBO;

    @Autowired
    private IGroupBO groupBO;

    @Override
    public void isAttention(String userId, String groupCode) {

        // 1-关注,0-提醒
        String type = EBoolean.YES.getCode();

        // 根据UserId和groupCode获取 关注记录
        Attention attention = attentionBO.getAttention(userId, groupCode, type);

        // 获取组合关注人数
        Long followNumber = groupBO.getGroup(groupCode).getFollowNumber();

        if (null == attention) {

            // 新增关注记录
            attentionBO.saveAttention(userId, groupCode, type);
            // 增加组合关注人数
            groupBO.editGroupFollowNumber(groupCode, followNumber + 1);

        } else {

            // 删除关注记录
            attentionBO.removeAttention(attention.getCode());
            // 减掉组合关注人数
            groupBO.editGroupFollowNumber(groupCode, followNumber - 1);

        }
    }

    @Override
    public void isRemind(String userId, String groupCode) {

        // 1-关注,0-提醒
        String type = EBoolean.NO.getCode();

        // 根据UserId和groupCode获取 提醒记录
        Attention remind = attentionBO.getAttention(userId, groupCode, type);

        if (null == remind) {

            // 新增提醒记录
            attentionBO.saveAttention(userId, groupCode, type);

        } else {

            // 删除提醒记录
            attentionBO.removeAttention(remind.getCode());

        }
    }

}
