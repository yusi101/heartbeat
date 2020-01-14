package com.ruoyi.project.business.comic.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.comic.mapper.ComicMapper;
import com.ruoyi.project.business.comic.domain.Comic;
import com.ruoyi.project.business.comic.service.IComicService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 动漫Service业务层处理
 * 
 * @author ys
 * @date 2019-12-24
 */
@Service
public class ComicServiceImpl implements IComicService 
{
    @Autowired
    private ComicMapper comicMapper;

    /**
     * 查询动漫
     * 
     * @param comicId 动漫ID
     * @return 动漫
     */
    @Override
    public Comic selectComicById(Integer comicId)
    {
        return comicMapper.selectComicById(comicId);
    }

    /**
     * 查询动漫列表
     * 
     * @param comic 动漫
     * @return 动漫
     */
    @Override
    public List<Comic> selectComicList(Comic comic)
    {
        return comicMapper.selectComicList(comic);
    }

    /**
     * 新增动漫
     * 
     * @param comic 动漫
     * @return 结果
     */
    @Override
    public int insertComic(Comic comic)
    {
        comic.setCreateTime(DateUtils.getNowDate());
        comic.setUpdateTime(DateUtils.getNowDate());
        return comicMapper.insertComic(comic);
    }

    /**
     * 修改动漫
     * 
     * @param comic 动漫
     * @return 结果
     */
    @Override
    public int updateComic(Comic comic)
    {
        comic.setUpdateTime(DateUtils.getNowDate());
        return comicMapper.updateComic(comic);
    }

    /**
     * 删除动漫对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteComicByIds(String ids)
    {
        return comicMapper.deleteComicByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除动漫信息
     * 
     * @param comicId 动漫ID
     * @return 结果
     */
    @Override
    public int deleteComicById(Integer comicId)
    {
        return comicMapper.deleteComicById(comicId);
    }

    @Override
    public List<Comic> selectComicListByNewest() {
        return comicMapper.selectComicListByNewest();
    }
}
