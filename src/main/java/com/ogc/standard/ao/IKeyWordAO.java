package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.KeyWord;

/**
 * 关键字
 * @author: silver 
 * @since: 2018年8月22日 上午10:49:33 
 * @history:
 */
public interface IKeyWordAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    // 添加关键字
    public void addKeyWord(String word, String level, String reaction,
            String remark, String updater);

    // 删除关键字
    public void dropKeyWord(Integer id);

    // 修改关键字
    public void editKeyWord(Integer id, String word, String level,
            String reaction, String remark, String updater);

    public Paginable<KeyWord> queryKeyWordPage(int start, int limit,
            KeyWord condition);

    public List<KeyWord> queryKeyWordList(KeyWord condition);

    public KeyWord getKeyWord(Integer id);

}
