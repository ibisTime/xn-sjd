package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IPresellInventoryDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.PresellInventory;

@Repository("presellInventoryDAOImpl")
public class PresellInventoryDAOImpl extends AMybatisTemplate
        implements IPresellInventoryDAO {

    @Override
    public int insert(PresellInventory data) {
        return super.insert(NAMESPACE.concat("insert_presellInventory"), data);
    }

    @Override
    public int delete(PresellInventory data) {
        return super.delete(NAMESPACE.concat("delete_presellInventory"), data);
    }

    @Override
    public int updateGroup(PresellInventory data) {
        return super.update(NAMESPACE.concat("update_group"), data);
    }

    @Override
    public PresellInventory select(PresellInventory condition) {
        return super.select(NAMESPACE.concat("select_presellInventory"),
            condition, PresellInventory.class);
    }

    @Override
    public long selectTotalCount(PresellInventory condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_presellInventory_count"), condition);
    }

    @Override
    public List<PresellInventory> selectList(PresellInventory condition) {
        return super.selectList(NAMESPACE.concat("select_presellInventory"),
            condition, PresellInventory.class);
    }

    @Override
    public List<PresellInventory> selectList(PresellInventory condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_presellInventory"),
            start, count, condition, PresellInventory.class);
    }

    @Override
    public List<PresellInventory> selectTreeNumberList(PresellInventory data) {
        return super.selectList(NAMESPACE.concat("select_treeNumber"), data,
            PresellInventory.class);
    }

}
