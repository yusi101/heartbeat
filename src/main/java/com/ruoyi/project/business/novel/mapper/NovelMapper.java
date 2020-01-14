package com.ruoyi.project.business.novel.mapper;

import com.ruoyi.project.business.movie.domain.Movie;
import com.ruoyi.project.business.novel.domain.Novel;
import java.util.List;

/**
 * 小说Mapper接口
 * 
 * @author ys
 * @date 2019-12-24
 */
public interface NovelMapper 
{
    /**
     * 查询小说
     * 
     * @param novelId 小说ID
     * @return 小说
     */
    public Novel selectNovelById(Integer novelId);

    /**
     * 查询小说列表
     * 
     * @param novel 小说
     * @return 小说集合
     */
    public List<Novel> selectNovelList(Novel novel);

    /**
     * 新增小说
     * 
     * @param novel 小说
     * @return 结果
     */
    public int insertNovel(Novel novel);

    /**
     * 修改小说
     * 
     * @param novel 小说
     * @return 结果
     */
    public int updateNovel(Novel novel);

    /**
     * 删除小说
     * 
     * @param novelId 小说ID
     * @return 结果
     */
    public int deleteNovelById(Integer novelId);

    /**
     * 批量删除小说
     * 
     * @param novelIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteNovelByIds(String[] novelIds);

    /**
     * 查询小说列表（精简查询前10条）
     *
     * @return 小说集合
     */
    public List<Novel> selectNovelListByNewest();
}
