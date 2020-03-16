package com.ruoyi.project.business.novel.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.novel.mapper.NovelMapper;
import com.ruoyi.project.business.novel.domain.Novel;
import com.ruoyi.project.business.novel.service.INovelService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 小说Service业务层处理
 * 
 * @author ys
 * @date 2019-12-24
 */
@Service
public class NovelServiceImpl implements INovelService 
{
    @Autowired
    private NovelMapper novelMapper;

    /**
     * 查询小说
     * 
     * @param novelId 小说ID
     * @return 小说
     */
    @Override
    public Novel selectNovelById(Integer novelId)
    {
        return novelMapper.selectNovelById(novelId);
    }

    /**
     * 查询小说列表
     * 
     * @param novel 小说
     * @return 小说
     */
    @Override
    public List<Novel> selectNovelList(Novel novel)
    {
        return novelMapper.selectNovelList(novel);
    }

    /**
     * 新增小说
     * 
     * @param novel 小说
     * @return 结果
     */
    @Override
    public int insertNovel(Novel novel)
    {
        novel.setCreateBy(ShiroUtils.getUserId().toString());
        novel.setCreateTime(DateUtils.getNowDate());
        return novelMapper.insertNovel(novel);
    }

    /**
     * 修改小说
     * 
     * @param novel 小说
     * @return 结果
     */
    @Override
    public int updateNovel(Novel novel)
    {
        novel.setUpdateBy(ShiroUtils.getUserId().toString());
        novel.setUpdateTime(DateUtils.getNowDate());
        return novelMapper.updateNovel(novel);
    }

    /**
     * 删除小说对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteNovelByIds(String ids)
    {
        return novelMapper.deleteNovelByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除小说信息
     * 
     * @param novelId 小说ID
     * @return 结果
     */
    @Override
    public int deleteNovelById(Integer novelId)
    {
        return novelMapper.deleteNovelById(novelId);
    }

    @Override
    public List<Novel> selectNovelListByNewest() {
        return novelMapper.selectNovelListByNewest();
    }
}
