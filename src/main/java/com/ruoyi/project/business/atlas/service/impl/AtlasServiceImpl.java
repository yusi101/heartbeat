package com.ruoyi.project.business.atlas.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.atlas.mapper.AtlasMapper;
import com.ruoyi.project.business.atlas.domain.Atlas;
import com.ruoyi.project.business.atlas.service.IAtlasService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 图册Service业务层处理
 * 
 * @author ys
 * @date 2019-12-24
 */
@Service
public class AtlasServiceImpl implements IAtlasService 
{
    @Autowired
    private AtlasMapper atlasMapper;

    /**
     * 查询图册
     * 
     * @param atlasId 图册ID
     * @return 图册
     */
    @Override
    public Atlas selectAtlasById(Integer atlasId)
    {
        return atlasMapper.selectAtlasById(atlasId);
    }

    /**
     * 查询图册列表
     * 
     * @param atlas 图册
     * @return 图册
     */
    @Override
    public List<Atlas> selectAtlasList(Atlas atlas)
    {
        return atlasMapper.selectAtlasList(atlas);
    }

    /**
     * 新增图册
     * 
     * @param atlas 图册
     * @return 结果
     */
    @Override
    public int insertAtlas(Atlas atlas)
    {
        atlas.setCreateTime(DateUtils.getNowDate());
        atlas.setUpdateTime(DateUtils.getNowDate());
        return atlasMapper.insertAtlas(atlas);
    }

    /**
     * 修改图册
     * 
     * @param atlas 图册
     * @return 结果
     */
    @Override
    public int updateAtlas(Atlas atlas)
    {
        atlas.setUpdateTime(DateUtils.getNowDate());
        return atlasMapper.updateAtlas(atlas);
    }

    /**
     * 删除图册对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAtlasByIds(String ids)
    {
        return atlasMapper.deleteAtlasByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除图册信息
     * 
     * @param atlasId 图册ID
     * @return 结果
     */
    @Override
    public int deleteAtlasById(Integer atlasId)
    {
        return atlasMapper.deleteAtlasById(atlasId);
    }

    @Override
    public List<Atlas> selectAtlasListByNewest() {
        return atlasMapper.selectAtlasListByNewest();
    }
}
