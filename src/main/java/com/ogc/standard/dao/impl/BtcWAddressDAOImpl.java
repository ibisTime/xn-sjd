/**
 * @Title BtcWAddressDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午7:52:29 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IBtcWAddressDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.BtcWAddress;

/** 
 * @author: taojian 
 * @since: 2018年9月11日 下午7:52:29 
 * @history:
 */
@Repository("btcWAddressDAOImpl")
public class BtcWAddressDAOImpl extends AMybatisTemplate
        implements IBtcWAddressDAO {

    @Override
    public int insert(BtcWAddress data) {
        return super.insert(NAMESPACE.concat("insert_btcWAddress"), data);
    }

    @Override
    public int delete(BtcWAddress data) {
        return super.delete(NAMESPACE.concat("delete_btcWAddress"), data);
    }

    /** 
     * @see com.ogc.standard.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public BtcWAddress select(BtcWAddress condition) {
        return super.select(NAMESPACE.concat("select_btcWAddress"), condition,
            BtcWAddress.class);
    }

    @Override
    public long selectTotalCount(BtcWAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_btcWAddress_count"), condition);
    }

    @Override
    public List<BtcWAddress> selectList(BtcWAddress condition) {
        return super.selectList(NAMESPACE.concat("select_btcWAddress"),
            condition, BtcWAddress.class);
    }

    @Override
    public List<BtcWAddress> selectList(BtcWAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_btcWAddress"), start,
            count, condition, BtcWAddress.class);

    }

    @Override
    public int updateStatus(BtcWAddress data) {
        return super.update(NAMESPACE.concat("update_abandon"), data);
    }

}
