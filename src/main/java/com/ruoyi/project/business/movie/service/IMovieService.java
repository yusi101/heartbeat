package com.ruoyi.project.business.movie.service;

import com.ruoyi.project.business.game.domain.Game;
import com.ruoyi.project.business.movie.domain.Movie;
import java.util.List;

/**
 * 电影Service接口
 * 
 * @author ys
 * @date 2019-12-24
 */
public interface IMovieService 
{
    /**
     * 查询电影
     * 
     * @param movieId 电影ID
     * @return 电影
     */
    public Movie selectMovieById(Integer movieId);

    /**
     * 查询电影列表
     * 
     * @param movie 电影
     * @return 电影集合
     */
    public List<Movie> selectMovieList(Movie movie);

    /**
     * 新增电影
     * 
     * @param movie 电影
     * @return 结果
     */
    public int insertMovie(Movie movie);

    /**
     * 修改电影
     * 
     * @param movie 电影
     * @return 结果
     */
    public int updateMovie(Movie movie);

    /**
     * 批量删除电影
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMovieByIds(String ids);

    /**
     * 删除电影信息
     * 
     * @param movieId 电影ID
     * @return 结果
     */
    public int deleteMovieById(Integer movieId);

    /**
     * 查询电影列表（精简查询前10条）
     *
     * @return 电影集合
     */
    public List<Movie> selectMovieListByNewest();
}
