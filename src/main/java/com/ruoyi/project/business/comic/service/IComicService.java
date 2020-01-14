package com.ruoyi.project.business.comic.service;

import com.ruoyi.project.business.cartoon.domain.Cartoon;
import com.ruoyi.project.business.comic.domain.Comic;
import java.util.List;

/**
 * 动漫Service接口
 * 
 * @author ys
 * @date 2019-12-24
 */
public interface IComicService 
{
    /**
     * 查询动漫
     * 
     * @param comicId 动漫ID
     * @return 动漫
     */
    public Comic selectComicById(Integer comicId);

    /**
     * 查询动漫列表
     * 
     * @param comic 动漫
     * @return 动漫集合
     */
    public List<Comic> selectComicList(Comic comic);

    /**
     * 新增动漫
     * 
     * @param comic 动漫
     * @return 结果
     */
    public int insertComic(Comic comic);

    /**
     * 修改动漫
     * 
     * @param comic 动漫
     * @return 结果
     */
    public int updateComic(Comic comic);

    /**
     * 批量删除动漫
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteComicByIds(String ids);

    /**
     * 删除动漫信息
     * 
     * @param comicId 动漫ID
     * @return 结果
     */
    public int deleteComicById(Integer comicId);

    /**
     * 查询动漫列表（精简查询前10条）
     *
     * @return 动漫集合
     */
    public List<Comic> selectComicListByNewest();
}
