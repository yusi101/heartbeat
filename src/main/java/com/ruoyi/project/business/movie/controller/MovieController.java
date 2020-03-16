package com.ruoyi.project.business.movie.controller;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.project.business.article.controller.ArticleController;
import com.ruoyi.project.business.atlas.domain.Atlas;
import com.ruoyi.project.business.game.domain.Game;
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
import com.ruoyi.project.business.movie.domain.Movie;
import com.ruoyi.project.business.movie.service.IMovieService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 电影Controller
 * 
 * @author ys
 * @date 2019-12-24
 */
@Controller
@RequestMapping("/business/movie")
public class MovieController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private String prefix = "business/movie";

    @Autowired
    private IMovieService movieService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("business:movie:view")
    @GetMapping()
    public String movie()
    {
        return prefix + "/movie";
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
    @GetMapping("/view/{movieId}")
    public String view(@PathVariable("movieId") Integer movieId, ModelMap mmap)
    {
        Movie movie = movieService.selectMovieById(movieId);
        if(movie.getMovieLabel()!=null && !movie.getMovieLabel().equals("")){
            String[] str = movie.getMovieLabel().split(",");
            List<String> LabelList = Arrays.asList(str);
            mmap.put("LabelList", LabelList);
        }
        mmap.put("movie", movie);
        return prefix + "/view";
    }
    /**
     * 查询电影列表
     */
    @RequiresPermissions("business:movie:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Movie movie)
    {
        startPage();
        movie.setStatus("0");
        List<Movie> list = movieService.selectMovieList(movie);
        for(int i = 0;i<list.size();i++){
            Movie mov = list.get(i);
            mov.setCreateBy(userService.selectUserById(Long.parseLong(mov.getCreateBy())).getUserName());
            mov.setUpdateBy(userService.selectUserById(Long.parseLong(mov.getUpdateBy())).getUserName());
            list.set(i,mov);
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
        List<Movie> list = movieService.selectMovieListByNewest();
        return JSON.toJSONString(list);
    }

    /** ---------------------------------    后台管理    ------------------------------------*/

    /**
     * 查询电影管理列表
     */
    @RequiresPermissions("business:movie:sysList")
    @PostMapping("/sysList")
    @ResponseBody
    public TableDataInfo sysList(Movie movie)
    {
        startPage();
        List<Movie> list = movieService.selectMovieList(movie);
        return getDataTable(list);
    }

    /**
     * 导出电影列表
     */
    @RequiresPermissions("business:movie:export")
    @Log(title = "电影", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Movie movie)
    {
        List<Movie> list = movieService.selectMovieList(movie);
        ExcelUtil<Movie> util = new ExcelUtil<Movie>(Movie.class);
        return util.exportExcel(list, "movie");
    }

    /**
     * 新增电影
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("uuid", StringUtils.getUUID());
        return prefix + "/add";
    }

    /**
     * 新增保存电影
     */
    @RequiresPermissions("business:movie:add")
    @Log(title = "电影", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Movie movie)
    {
        return toAjax(movieService.insertMovie(movie));
    }

    /**
     * 修改电影
     */
    @GetMapping("/edit/{movieId}")
    public String edit(@PathVariable("movieId") Integer movieId, ModelMap mmap)
    {
        Movie movie = movieService.selectMovieById(movieId);
        mmap.put("movie", movie);
        return prefix + "/edit";
    }

    /**
     * 修改保存电影
     */
    @RequiresPermissions("business:movie:edit")
    @Log(title = "电影", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Movie movie)
    {
        return toAjax(movieService.updateMovie(movie));
    }

    /**
     * 删除电影
     */
    @RequiresPermissions("business:movie:remove")
    @Log(title = "电影", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(movieService.deleteMovieByIds(ids));
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
