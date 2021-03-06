package com.ruoyi.project.business.game.service;

import com.ruoyi.project.business.comic.domain.Comic;
import com.ruoyi.project.business.game.domain.Game;
import java.util.List;

/**
 * 游戏Service接口
 * 
 * @author ys
 * @date 2019-12-24
 */
public interface IGameService 
{
    /**
     * 查询游戏
     * 
     * @param gameId 游戏ID
     * @return 游戏
     */
    public Game selectGameById(Integer gameId);

    /**
     * 查询游戏列表
     * 
     * @param game 游戏
     * @return 游戏集合
     */
    public List<Game> selectGameList(Game game);

    /**
     * 新增游戏
     * 
     * @param game 游戏
     * @return 结果
     */
    public int insertGame(Game game);

    /**
     * 修改游戏
     * 
     * @param game 游戏
     * @return 结果
     */
    public int updateGame(Game game);

    /**
     * 批量删除游戏
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGameByIds(String ids);

    /**
     * 删除游戏信息
     * 
     * @param gameId 游戏ID
     * @return 结果
     */
    public int deleteGameById(Integer gameId);

    /**
     * 查询游戏列表（精简查询前10条）
     *
     * @return 游戏集合
     */
    public List<Game> selectGameListByNewest();
}
