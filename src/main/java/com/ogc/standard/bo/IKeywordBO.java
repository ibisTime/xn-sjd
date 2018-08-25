package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Keyword;

/**
 * 关键字
 * @author: silver 
 * @since: 2018年8月22日 上午10:36:36 
 * @history:
 */
public interface IKeywordBO extends IPaginableBO<Keyword> {

    public boolean isKeywordExist(Integer id);

    // 关键字是否存在
    public boolean isKeywordExist(String word);

    // 添加关键字
    public void saveKeyword(String word, String level, String reaction,
            String remark, String updater);

    // 删除关键字
    public void removeKeyword(Integer id);

    // 修改关键字
    public void refreshKeyword(Integer id, String word, String level,
            String reaction, String remark, String updater);

    // 检查关键字
    public List<Keyword> checkContent(String content);

    // 替换关键字
    public String replaceKeyword(String content, String word);

    public List<Keyword> queryKeywordList(Keyword condition);

    public Keyword getKeyword(Integer id);

}
