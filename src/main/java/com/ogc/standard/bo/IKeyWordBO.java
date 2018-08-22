package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.KeyWord;

/**
 * 关键字
 * @author: silver 
 * @since: 2018年8月22日 上午10:36:36 
 * @history:
 */
public interface IKeyWordBO extends IPaginableBO<KeyWord> {

    public boolean isKeyWordExist(Integer id);

    // 关键字是否存在
    public boolean isKeyWordExist(String word);

    // 添加关键字
    public void saveKeyWord(String word, String level, String reaction,
            String remark, String updater);

    // 删除关键字
    public void removeKeyWord(Integer id);

    // 修改关键字
    public void refreshKeyWord(Integer id, String word, String level,
            String reaction, String remark, String updater);

    public List<KeyWord> queryKeyWordList(KeyWord condition);

    public KeyWord getKeyWord(Integer id);

}
