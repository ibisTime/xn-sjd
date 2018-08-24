/**
 * @Title SmsDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午1:32:00 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISmsDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Sms;

/** 
 * @author: dl 
 * @since: 2018年8月22日 下午1:32:00 
 * @history:
 */
@Repository("smsDAOImpl")
public class SmsDAOImpl extends AMybatisTemplate implements ISmsDAO {

    @Override
    public int insert(Sms data) {
        return super.insert(NAMESPACE.concat("insert_sms"), data);
    }

    @Override
    public int delete(Sms data) {
        return 0;
    }

    @Override
    public Sms select(Sms condition) {
        return super.select(NAMESPACE.concat("select_sms"), condition,
            Sms.class);
    }

    @Override
    public long selectTotalCount(Sms condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_sms_count"),
            condition);
    }

    @Override
    public List<Sms> selectList(Sms condition) {
        return super.selectList(NAMESPACE.concat("select_sms"), condition,
            Sms.class);
    }

    @Override
    public List<Sms> selectList(Sms condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_sms"), start, count,
            condition, Sms.class);

    }

    @Override
    public int updateStatus(Sms data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateSms(Sms data) {
        return super.update(NAMESPACE.concat("update_sms"), data);
    }

}
