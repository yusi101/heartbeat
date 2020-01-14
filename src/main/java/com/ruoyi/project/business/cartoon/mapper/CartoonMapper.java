package com.ruoyi.project.business.cartoon.mapper;

import com.ruoyi.project.business.atlas.domain.Atlas;
import com.ruoyi.project.business.cartoon.domain.Cartoon;
import java.util.List;

/**
 * 漫画Mapper接口
 * 
 * @author ys
 * @date 2019-12-24
 */
public interface CartoonMapper 
{
    /**
     * 查询漫画
     * 
     * @param cartoonId 漫画ID
     * @return 漫画
     */
    public Cartoon selectCartoonById(Integer cartoonId);

    /**
     * 查询漫画列表
     * 
     * @param cartoon 漫画
     * @return 漫画集合
     */
    public List<Cartoon> selectCartoonList(Cartoon cartoon);

    /**
     * 新增漫画
     * 
     * @param cartoon 漫画
     * @return 结果
     */
    public int insertCartoon(Cartoon cartoon);

    /**
     * 修改漫画
     * 
     * @param cartoon 漫画
     * @return 结果
     */
    public int updateCartoon(Cartoon cartoon);

    /**
     * 删除漫画
     * 
     * @param cartoonId 漫画ID
     * @return 结果
     */
    public int deleteCartoonById(Integer cartoonId);

    /**
     * 批量删除漫画
     * 
     * @param cartoonIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCartoonByIds(String[] cartoonIds);

    /**
     * 查询漫画列表（精简查询前10条）
     *
     * @return 漫画集合
     */
    public List<Cartoon> selectCartoonListByNewest();
}
