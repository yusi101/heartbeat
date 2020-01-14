package com.ruoyi.project.business.movie.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.movie.mapper.MovieMapper;
import com.ruoyi.project.business.movie.domain.Movie;
import com.ruoyi.project.business.movie.service.IMovieService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 电影Service业务层处理
 * 
 * @author ys
 * @date 2019-12-24
 */
@Service
public class MovieServiceImpl implements IMovieService 
{
    @Autowired
    private MovieMapper movieMapper;

    /**
     * 查询电影
     * 
     * @param movieId 电影ID
     * @return 电影
     */
    @Override
    public Movie selectMovieById(Integer movieId)
    {
        return movieMapper.selectMovieById(movieId);
    }

    /**
     * 查询电影列表
     * 
     * @param movie 电影
     * @return 电影
     */
    @Override
    public List<Movie> selectMovieList(Movie movie)
    {
        return movieMapper.selectMovieList(movie);
    }

    /**
     * 新增电影
     * 
     * @param movie 电影
     * @return 结果
     */
    @Override
    public int insertMovie(Movie movie)
    {
        movie.setCreateTime(DateUtils.getNowDate());
        movie.setUpdateTime(DateUtils.getNowDate());
        return movieMapper.insertMovie(movie);
    }

    /**
     * 修改电影
     * 
     * @param movie 电影
     * @return 结果
     */
    @Override
    public int updateMovie(Movie movie)
    {
        movie.setUpdateTime(DateUtils.getNowDate());
        return movieMapper.updateMovie(movie);
    }

    /**
     * 删除电影对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMovieByIds(String ids)
    {
        return movieMapper.deleteMovieByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除电影信息
     * 
     * @param movieId 电影ID
     * @return 结果
     */
    @Override
    public int deleteMovieById(Integer movieId)
    {
        return movieMapper.deleteMovieById(movieId);
    }

    @Override
    public List<Movie> selectMovieListByNewest() {
        return movieMapper.selectMovieListByNewest();
    }
}
