package com.ruoyi.project.business.comic.controller;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.project.business.article.controller.ArticleController;
import com.ruoyi.project.business.atlas.domain.Atlas;
import com.ruoyi.project.business.cartoon.domain.Cartoon;
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
import com.ruoyi.project.business.comic.domain.Comic;
import com.ruoyi.project.business.comic.service.IComicService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 动漫Controller
 * 
 * @author ys
 * @date 2019-12-24
 */
@Controller
@RequestMapping("/business/comic")
public class ComicController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private String prefix = "business/comic";

    @Autowired
    private IComicService comicService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("business:comic:view")
    @GetMapping()
    public String comic()
    {
        return prefix + "/comic";
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
     * 查看动漫
     */
    @GetMapping("/view/{comicId}")
    public String view(@PathVariable("comicId") Integer comicId, ModelMap mmap)
    {
        Comic comic = comicService.selectComicById(comicId);
        if(comic.getComicLabel()!=null && !comic.getComicLabel().equals("")){
            String[] str = comic.getComicLabel().split(",");
            List<String> LabelList = Arrays.asList(str);
            mmap.put("LabelList", LabelList);
        }
        mmap.put("comic", comic);
        return prefix + "/view";
    }
    /**
     * 查询动漫列表
     */
    @RequiresPermissions("business:comic:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Comic comic)
    {
        startPage();
        comic.setStatus("0");
        List<Comic> list = comicService.selectComicList(comic);
        for(int i = 0;i<list.size();i++){
            Comic com = list.get(i);
            com.setCreateBy(userService.selectUserById(Long.parseLong(com.getCreateBy())).getUserName());
            com.setUpdateBy(userService.selectUserById(Long.parseLong(com.getUpdateBy())).getUserName());
            list.set(i,com);
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
        List<Comic> list = comicService.selectComicListByNewest();
        return JSON.toJSONString(list);
    }
    /** ---------------------------------    后台管理    ------------------------------------*/

    /**
     * 查询动漫管理列表
     */
    @RequiresPermissions("business:comic:sysList")
    @PostMapping("/sysList")
    @ResponseBody
    public TableDataInfo sysList(Comic comic)
    {
        startPage();
        List<Comic> list = comicService.selectComicList(comic);
        return getDataTable(list);
    }

    /**
     * 导出动漫列表
     */
    @RequiresPermissions("business:comic:export")
    @Log(title = "动漫", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Comic comic)
    {
        List<Comic> list = comicService.selectComicList(comic);
        ExcelUtil<Comic> util = new ExcelUtil<Comic>(Comic.class);
        return util.exportExcel(list, "comic");
    }

    /**
     * 新增动漫
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("uuid", StringUtils.getUUID());
        return prefix + "/add";
    }

    /**
     * 新增保存动漫
     */
    @RequiresPermissions("business:comic:add")
    @Log(title = "动漫", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Comic comic)
    {
        return toAjax(comicService.insertComic(comic));
    }

    /**
     * 修改动漫
     */
    @GetMapping("/edit/{comicId}")
    public String edit(@PathVariable("comicId") Integer comicId, ModelMap mmap)
    {
        Comic comic = comicService.selectComicById(comicId);
        mmap.put("comic", comic);
        return prefix + "/edit";
    }

    /**
     * 修改保存动漫
     */
    @RequiresPermissions("business:comic:edit")
    @Log(title = "动漫", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Comic comic)
    {
        return toAjax(comicService.updateComic(comic));
    }

    /**
     * 删除动漫
     */
    @RequiresPermissions("business:comic:remove")
    @Log(title = "动漫", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(comicService.deleteComicByIds(ids));
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
