package com.ruoyi.project.business.game.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.game.mapper.GameMapper;
import com.ruoyi.project.business.game.domain.Game;
import com.ruoyi.project.business.game.service.IGameService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 游戏Service业务层处理
 * 
 * @author ys
 * @date 2019-12-24
 */
@Service
public class GameServiceImpl implements IGameService 
{
    @Autowired
    private GameMapper gameMapper;

    /**
     * 查询游戏
     * 
     * @param gameId 游戏ID
     * @return 游戏
     */
    @Override
    public Game selectGameById(Integer gameId)
    {
        return gameMapper.selectGameById(gameId);
    }

    /**
     * 查询游戏列表
     * 
     * @param game 游戏
     * @return 游戏
     */
    @Override
    public List<Game> selectGameList(Game game)
    {
        return gameMapper.selectGameList(game);
    }

    /**
     * 新增游戏
     * 
     * @param game 游戏
     * @return 结果
     */
    @Override
    public int insertGame(Game game)
    {
        game.setCreateTime(DateUtils.getNowDate());
        game.setUpdateTime(DateUtils.getNowDate());
        return gameMapper.insertGame(game);
    }

    /**
     * 修改游戏
     * 
     * @param game 游戏
     * @return 结果
     */
    @Override
    public int updateGame(Game game)
    {
        game.setUpdateTime(DateUtils.getNowDate());
        return gameMapper.updateGame(game);
    }

    /**
     * 删除游戏对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGameByIds(String ids)
    {
        return gameMapper.deleteGameByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除游戏信息
     * 
     * @param gameId 游戏ID
     * @return 结果
     */
    @Override
    public int deleteGameById(Integer gameId)
    {
        return gameMapper.deleteGameById(gameId);
    }

    @Override
    public List<Game> selectGameListByNewest() {
        return gameMapper.selectGameListByNewest();
    }
}
