package com.ruoyi.project.business.novel.controller;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.project.business.article.controller.ArticleController;
import com.ruoyi.project.business.atlas.domain.Atlas;
import com.ruoyi.project.business.movie.domain.Movie;
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
import com.ruoyi.project.business.novel.domain.Novel;
import com.ruoyi.project.business.novel.service.INovelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 小说Controller
 * 
 * @author ys
 * @date 2019-12-24
 */
@Controller
@RequestMapping("/business/novel")
public class NovelController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private String prefix = "business/novel";

    @Autowired
    private INovelService novelService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("business:novel:view")
    @GetMapping()
    public String novel()
    {
        return prefix + "/novel";
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
     * 查看小说
     */
    @GetMapping("/view/{novelId}")
    public String view(@PathVariable("novelId") Integer novelId, ModelMap mmap)
    {
        Novel novel = novelService.selectNovelById(novelId);
        if(novel.getNovelLabel()!=null && !novel.getNovelLabel().equals("")){
            String[] str = novel.getNovelLabel().split(",");
            List<String> LabelList = Arrays.asList(str);
            mmap.put("LabelList", LabelList);
        }
        mmap.put("novel", novel);
        return prefix + "/view";
    }
    /**
     * 查询小说列表
     */
    @RequiresPermissions("business:novel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Novel novel)
    {
        startPage();
        novel.setStatus("0");
        List<Novel> list = novelService.selectNovelList(novel);
        for(int i = 0;i<list.size();i++){
            Novel nov = list.get(i);
            nov.setUpdateBy(userService.selectUserById(Long.parseLong(nov.getUpdateBy())).getUserName());
            list.set(i,nov);
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
        List<Novel> list = novelService.selectNovelListByNewest();
        return JSON.toJSONString(list);
    }

    /** ---------------------------------    后台管理    ------------------------------------*/

    /**
     * 查询小说列表
     */
    @RequiresPermissions("business:novel:sysList")
    @PostMapping("/sysList")
    @ResponseBody
    public TableDataInfo sysList(Novel novel)
    {
        startPage();
        List<Novel> list = novelService.selectNovelList(novel);
        return getDataTable(list);
    }

    /**
     * 导出小说列表
     */
    @RequiresPermissions("business:novel:export")
    @Log(title = "小说", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Novel novel)
    {
        List<Novel> list = novelService.selectNovelList(novel);
        ExcelUtil<Novel> util = new ExcelUtil<Novel>(Novel.class);
        return util.exportExcel(list, "novel");
    }

    /**
     * 新增小说
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("uuid", StringUtils.getUUID());
        return prefix + "/add";
    }

    /**
     * 新增保存小说
     */
    @RequiresPermissions("business:novel:add")
    @Log(title = "小说", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Novel novel)
    {
        return toAjax(novelService.insertNovel(novel));
    }

    /**
     * 修改小说
     */
    @GetMapping("/edit/{novelId}")
    public String edit(@PathVariable("novelId") Integer novelId, ModelMap mmap)
    {
        Novel novel = novelService.selectNovelById(novelId);
        mmap.put("novel", novel);
        return prefix + "/edit";
    }

    /**
     * 修改保存小说
     */
    @RequiresPermissions("business:novel:edit")
    @Log(title = "小说", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Novel novel)
    {
        return toAjax(novelService.updateNovel(novel));
    }

    /**
     * 删除小说
     */
    @RequiresPermissions("business:novel:remove")
    @Log(title = "小说", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(novelService.deleteNovelByIds(ids));
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
