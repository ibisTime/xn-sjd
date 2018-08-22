package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IKeyWordDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.KeyWord;

/**
 * 关键字
 * @author: silver 
 * @since: 2018年8月22日 上午10:35:28 
 * @history:
 */
@Repository("keyWordDAOImpl")
public class KeyWordDAOImpl extends AMybatisTemplate implements IKeyWordDAO {

    @Override
    public int insert(KeyWord data) {
        return super.insert(NAMESPACE.concat("insert_keyWord"), data);
    }

    @Override
    public int delete(KeyWord data) {
        return super.delete(NAMESPACE.concat("delete_keyWord"), data);
    }

    @Override
    public int updateKeyWord(KeyWord data) {
        return super.update(NAMESPACE.concat("update_keyWord"), data);
    }

    @Override
    public KeyWord select(KeyWord condition) {
        return super.select(NAMESPACE.concat("select_keyWord"), condition,
            KeyWord.class);
    }

    @Override
    public long selectTotalCount(KeyWord condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_keyWord_count"),
            condition);
    }

    @Override
    public List<KeyWord> selectList(KeyWord condition) {
        return super.selectList(NAMESPACE.concat("select_keyWord"), condition,
            KeyWord.class);
    }

    @Override
    public List<KeyWord> selectList(KeyWord condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_keyWord"), start,
            count, condition, KeyWord.class);
    }

}
