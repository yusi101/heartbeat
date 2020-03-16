package com.ruoyi.project.business.atlas.controller;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.project.business.article.controller.ArticleController;
import com.ruoyi.project.business.article.domain.Article;
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
import com.ruoyi.project.business.atlas.domain.Atlas;
import com.ruoyi.project.business.atlas.service.IAtlasService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图册Controller
 * 
 * @author ys
 * @date 2019-12-24
 */
@Controller
@RequestMapping("/business/atlas")
public class AtlasController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private String prefix = "business/atlas";

    @Autowired
    private IAtlasService atlasService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("business:atlas:view")
    @GetMapping()
    public String atlas()
    {
        return prefix + "/atlas";
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
     * 查看图册
     */
    @GetMapping("/view/{atlasId}")
    public String view(@PathVariable("atlasId") Integer atlasId, ModelMap mmap)
    {
        Atlas atlas = atlasService.selectAtlasById(atlasId);
        if(atlas.getAtlasLabel()!=null && !atlas.getAtlasLabel().equals("")){
            String[] str = atlas.getAtlasLabel().split(",");
            List<String> LabelList = Arrays.asList(str);
            mmap.put("LabelList", LabelList);
        }
        mmap.put("atlas", atlas);
        return prefix + "/view";
    }
    /**
     * 查询图册列表
     */
    @RequiresPermissions("business:atlas:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Atlas atlas)
    {
        startPage();
        atlas.setStatus("0");
        List<Atlas> list = atlasService.selectAtlasList(atlas);
        for(int i = 0;i<list.size();i++){
            Atlas atl = list.get(i);
            atl.setUpdateBy(userService.selectUserById(Long.parseLong(atl.getUpdateBy())).getUserName());
            list.set(i,atl);
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
        List<Atlas> list = atlasService.selectAtlasListByNewest();
        return JSON.toJSONString(list);
    }

    /** ---------------------------------    后台管理    ------------------------------------*/

    /**
     * 查询图册管理列表
     */
    @RequiresPermissions("business:atlas:sysList")
    @PostMapping("/sysList")
    @ResponseBody
    public TableDataInfo sysList(Atlas atlas)
    {
        startPage();
        List<Atlas> list = atlasService.selectAtlasList(atlas);
        return getDataTable(list);
    }

    /**
     * 导出图册列表
     */
    @RequiresPermissions("business:atlas:export")
    @Log(title = "图册", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Atlas atlas)
    {
        List<Atlas> list = atlasService.selectAtlasList(atlas);
        ExcelUtil<Atlas> util = new ExcelUtil<Atlas>(Atlas.class);
        return util.exportExcel(list, "atlas");
    }

    /**
     * 新增图册
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("uuid", StringUtils.getUUID());
        return prefix + "/add";
    }

    /**
     * 新增保存图册
     */
    @RequiresPermissions("business:atlas:add")
    @Log(title = "图册", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Atlas atlas)
    {
        return toAjax(atlasService.insertAtlas(atlas));
    }

    /**
     * 修改图册
     */
    @GetMapping("/edit/{atlasId}")
    public String edit(@PathVariable("atlasId") Integer atlasId, ModelMap mmap)
    {
        Atlas atlas = atlasService.selectAtlasById(atlasId);
        mmap.put("atlas", atlas);
        return prefix + "/edit";
    }

    /**
     * 修改保存图册
     */
    @RequiresPermissions("business:atlas:edit")
    @Log(title = "图册", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Atlas atlas)
    {
        return toAjax(atlasService.updateAtlas(atlas));
    }

    /**
     * 删除图册
     */
    @RequiresPermissions("business:atlas:remove")
    @Log(title = "图册", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(atlasService.deleteAtlasByIds(ids));
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
