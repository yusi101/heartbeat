package com.ruoyi.project.business.cartoon.controller;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.project.business.article.controller.ArticleController;
import com.ruoyi.project.business.atlas.domain.Atlas;
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
import com.ruoyi.project.business.cartoon.domain.Cartoon;
import com.ruoyi.project.business.cartoon.service.ICartoonService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 漫画Controller
 * 
 * @author ys
 * @date 2019-12-24
 */
@Controller
@RequestMapping("/business/cartoon")
public class CartoonController extends BaseController
{

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private String prefix = "business/cartoon";

    @Autowired
    private ICartoonService cartoonService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("business:cartoon:view")
    @GetMapping()
    public String cartoon()
    {
        return prefix + "/cartoon";
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
     * 查看漫画
     */
    @GetMapping("/view/{cartoonId}")
    public String view(@PathVariable("cartoonId") Integer cartoonId, ModelMap mmap)
    {
        Cartoon cartoon = cartoonService.selectCartoonById(cartoonId);
        if(cartoon.getCartoonLabel()!=null && !cartoon.getCartoonLabel().equals("")){
            String[] str = cartoon.getCartoonLabel().split(",");
            List<String> LabelList = Arrays.asList(str);
            mmap.put("LabelList", LabelList);
        }
        mmap.put("cartoon", cartoon);
        return prefix + "/view";
    }
    /**
     * 查询漫画列表
     */
    @RequiresPermissions("business:cartoon:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Cartoon cartoon)
    {
        startPage();
        cartoon.setStatus("0");
        List<Cartoon> list = cartoonService.selectCartoonList(cartoon);
        for(int i = 0;i<list.size();i++){
            Cartoon car = list.get(i);
            car.setUpdateBy(userService.selectUserById(Long.parseLong(car.getUpdateBy())).getUserName());
            list.set(i,car);
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
        List<Cartoon> list = cartoonService.selectCartoonListByNewest();
        return JSON.toJSONString(list);
    }

    /** ---------------------------------    后台管理    ------------------------------------*/

    /**
     * 查询漫画管理列表
     */
    @RequiresPermissions("business:cartoon:sysList")
    @PostMapping("/sysList")
    @ResponseBody
    public TableDataInfo sysList(Cartoon cartoon)
    {
        startPage();
        List<Cartoon> list = cartoonService.selectCartoonList(cartoon);
        return getDataTable(list);
    }

    /**
     * 导出漫画列表
     */
    @RequiresPermissions("business:cartoon:export")
    @Log(title = "漫画", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Cartoon cartoon)
    {
        List<Cartoon> list = cartoonService.selectCartoonList(cartoon);
        ExcelUtil<Cartoon> util = new ExcelUtil<Cartoon>(Cartoon.class);
        return util.exportExcel(list, "cartoon");
    }

    /**
     * 新增漫画
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("uuid", StringUtils.getUUID());
        return prefix + "/add";
    }

    /**
     * 新增保存漫画
     */
    @RequiresPermissions("business:cartoon:add")
    @Log(title = "漫画", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Cartoon cartoon)
    {
        return toAjax(cartoonService.insertCartoon(cartoon));
    }

    /**
     * 修改漫画
     */
    @GetMapping("/edit/{cartoonId}")
    public String edit(@PathVariable("cartoonId") Integer cartoonId, ModelMap mmap)
    {
        Cartoon cartoon = cartoonService.selectCartoonById(cartoonId);
        mmap.put("cartoon", cartoon);
        return prefix + "/edit";
    }

    /**
     * 修改保存漫画
     */
    @RequiresPermissions("business:cartoon:edit")
    @Log(title = "漫画", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Cartoon cartoon)
    {
        return toAjax(cartoonService.updateCartoon(cartoon));
    }

    /**
     * 删除漫画
     */
    @RequiresPermissions("business:cartoon:remove")
    @Log(title = "漫画", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cartoonService.deleteCartoonByIds(ids));
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
                String fileName = FileUploadUtils.uploadByUuid(RuoYiConfig.getUploadPath(), file,uuid,"cartoon/");
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
