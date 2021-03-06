package com.ruoyi.project.business.atlas.service;

import com.ruoyi.project.business.article.domain.Article;
import com.ruoyi.project.business.atlas.domain.Atlas;
import java.util.List;

/**
 * 图册Service接口
 * 
 * @author ys
 * @date 2019-12-24
 */
public interface IAtlasService 
{
    /**
     * 查询图册
     * 
     * @param atlasId 图册ID
     * @return 图册
     */
    public Atlas selectAtlasById(Integer atlasId);

    /**
     * 查询图册列表
     * 
     * @param atlas 图册
     * @return 图册集合
     */
    public List<Atlas> selectAtlasList(Atlas atlas);

    /**
     * 新增图册
     * 
     * @param atlas 图册
     * @return 结果
     */
    public int insertAtlas(Atlas atlas);

    /**
     * 修改图册
     * 
     * @param atlas 图册
     * @return 结果
     */
    public int updateAtlas(Atlas atlas);

    /**
     * 批量删除图册
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAtlasByIds(String ids);

    /**
     * 删除图册信息
     * 
     * @param atlasId 图册ID
     * @return 结果
     */
    public int deleteAtlasById(Integer atlasId);

    /**
     * 查询图册列表（精简查询前10条）
     *
     * @return 图册集合
     */
    public List<Atlas> selectAtlasListByNewest();
}
