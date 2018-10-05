package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.ICarbonBubbleOrderBO;
import com.ogc.standard.bo.IGiveTreeRecordBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IVisitorBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.GiveTreeRecord;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.Visitor;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;

@Service
public class AdoptOrderTreeAOImpl implements IAdoptOrderTreeAO {

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IGiveTreeRecordBO giveTreeRecordBO;

    @Autowired
    private ICarbonBubbleOrderBO carbonBubbleOrderBO;

    @Autowired
    private IBizLogBO bizLogBO;

    @Autowired
    private IVisitorBO visitorBO;

    public void doDailyAdoptOrderTree() {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(condition);
        for (AdoptOrderTree adoptOrderTree : list) {
        }
    }

    @Override
    public void giveTree(String code, String toUserId, String userId) {
        // 更改认养权持有人
        AdoptOrderTree data = adoptOrderTreeBO.getAdoptOrderTree(code);
        data.setCurrentHolder(toUserId);
        adoptOrderTreeBO.giveTree(data);
        // 新增赠送树记录
        GiveTreeRecord record = new GiveTreeRecord();
        record.setAdoptTreeCode(code);
        record.setUserId(userId);
        record.setToUserId(toUserId);
        record.setCreateDatetime(new Date());
        giveTreeRecordBO.saveGiveTreeRecord(record);
    }

    @Override
    public long leaveMessage(String code, String note, String userId) {
        return bizLogBO.leaveMessage(code, note, userId);
    }

    @Override
    public String visit(String code, String userId) {
        // 查询前10条是否有此来访人，如果存在则删除
        Visitor visitor = visitorBO.getTopTenVisitor(code, userId);
        if (null != visitor) {
            visitorBO.removeVisitor(visitor.getCode());
        }

        return visitorBO.saveVisitor(code, userId);
    }

    @Override
    public Paginable<AdoptOrderTree> queryAdoptOrderTreePage(int start,
            int limit, AdoptOrderTree condition) {
        Paginable<AdoptOrderTree> page = adoptOrderTreeBO.getPaginable(start,
            limit, condition);
        for (AdoptOrderTree adoptOrderTree : page.getList()) {
            initAdoptOrderTree(adoptOrderTree);
        }
        return page;
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(
            AdoptOrderTree condition) {
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(condition);
        for (AdoptOrderTree adoptOrderTree : list) {
            initAdoptOrderTree(adoptOrderTree);
        }
        return list;
    }

    @Override
    public AdoptOrderTree getAdoptOrderTree(String code) {
        AdoptOrderTree data = adoptOrderTreeBO.getAdoptOrderTree(code);
        initAdoptOrderTree(data);
        return data;
    }

    private void initAdoptOrderTree(AdoptOrderTree data) {
        Tree tree = treeBO.getTreeByTreeNumber(data.getTreeNumber());
        data.setTree(tree);
    }

}
