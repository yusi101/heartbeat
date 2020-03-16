package com.ruoyi.project.business.game.controller;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.project.business.article.controller.ArticleController;
import com.ruoyi.project.business.atlas.domain.Atlas;
import com.ruoyi.project.business.comic.domain.Comic;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.business.game.domain.Game;
import com.ruoyi.project.business.game.service.IGameService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 游戏Controller
 * 
 * @author ys
 * @date 2019-12-24
 */
@Controller
@RequestMapping("/business/game")
public class GameController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private String prefix = "business/game";

    @Autowired
    private IGameService gameService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("business:game:view")
    @GetMapping()
    public String game()
    {
        return prefix + "/game";
    }

    /** ---------------------------------    前台业务    ------------------------------------*/

    /**
     * 业务展示界面
     * @return
     */
    @GetMapping("/list")
    public String list()
    {
        return prefix + "/list";
    }
    /**
     * 查看游戏
     */
    @GetMapping("/view/{gameId}")
    public String view(@PathVariable("gameId") Integer gameId, ModelMap mmap)
    {
        Game game = gameService.selectGameById(gameId);
        if(game.getGameLabel()!=null && !game.getGameLabel().equals("")){
            String[] str = game.getGameLabel().split(",");
            List<String> LabelList = Arrays.asList(str);
            mmap.put("LabelList", LabelList);
        }
        mmap.put("game", game);
        return prefix + "/view";
    }
    /**
     * 查询游戏列表
     */
    @RequiresPermissions("business:game:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Game game)
    {
        startPage();
        game.setStatus("0");
        List<Game> list = gameService.selectGameList(game);
        for(int i = 0;i<list.size();i++){
            Game ga = list.get(i);
            ga.setUpdateBy(userService.selectUserById(Long.parseLong(ga.getUpdateBy())).getUserName());
            list.set(i,ga);
        }
        return getDataTable(list);
    }

    /**
     * 最新排行版 （查询前10条）
     */
    @PostMapping("/newest")
    @ResponseBody
    public String newest(ModelMap map)
    {
        List<Game> list = gameService.selectGameListByNewest();
        return JSON.toJSONString(list);
    }

    /** ---------------------------------    后台管理    ------------------------------------*/

    /**
     * 查询游戏管理列表
     */
    @RequiresPermissions("business:game:sysList")
    @PostMapping("/sysList")
    @ResponseBody
    public TableDataInfo sysList(Game game)
    {
        startPage();
        List<Game> list = gameService.selectGameList(game);
        return getDataTable(list);
    }

    /**
     * 导出游戏列表
     */
    @RequiresPermissions("business:game:export")
    @Log(title = "游戏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Game game)
    {
        List<Game> list = gameService.selectGameList(game);
        ExcelUtil<Game> util = new ExcelUtil<Game>(Game.class);
        return util.exportExcel(list, "game");
    }

    /**
     * 新增游戏
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("uuid", StringUtils.getUUID());
        return prefix + "/add";
    }

    /**
     * 新增保存游戏
     */
    @RequiresPermissions("business:game:add")
    @Log(title = "游戏", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Game game)
    {
        return toAjax(gameService.insertGame(game));
    }

    /**
     * 修改游戏
     */
    @GetMapping("/edit/{gameId}")
    public String edit(@PathVariable("gameId") Integer gameId, ModelMap mmap)
    {
        Game game = gameService.selectGameById(gameId);
        mmap.put("game", game);
        return prefix + "/edit";
    }

    /**
     * 修改保存游戏
     */
    @RequiresPermissions("business:game:edit")
    @Log(title = "游戏", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Game game)
    {
        return toAjax(gameService.updateGame(game));
    }

    /**
     * 删除游戏
     */
    @RequiresPermissions("business:game:remove")
    @Log(title = "游戏", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gameService.deleteGameByIds(ids));
    }

    /**
     * 打来选择预览图片
     */
    @GetMapping("/img")
    public String profile()
    {
        return prefix + "/img";
    }

    /**
     * 保存图片 路径为 年月/uuid
     */
    @Log(title = "保存图片", businessType = BusinessType.UPDATE)
    @PostMapping("/updateImg")
    @ResponseBody
    public AjaxResult updateImg(@RequestParam("avatarfile") MultipartFile file,
                                @RequestParam("uuid") String uuid)
    {
        try
        {
            if (!file.isEmpty())
            {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.uploadByUuid(RuoYiConfig.getUploadPath(), file,uuid,"atlas/");
                String url = serverConfig.getUrl() + fileName;
                AjaxResult ajax = AjaxResult.success();
                ajax.put("fileName", fileName);
                ajax.put("url", url);
                return success(fileName);
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("上传图片失败！", e);
            return error(e.getMessage());
        }
    }
}
