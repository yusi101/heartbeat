package com.ruoyi.project.business.article.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
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
import com.ruoyi.project.business.article.domain.Article;
import com.ruoyi.project.business.article.service.IArticleService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文章Controller
 * 
 * @author ys
 * @date 2019-12-24
 */
@Controller
@RequestMapping("/business/article")
public class ArticleController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private String prefix = "business/article";

    @Autowired
    private IArticleService articleService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("business:article:view")
    @GetMapping()
    public String article()
    {
        return prefix + "/article";
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
     * 查看文章
     */
    @GetMapping("/view/{articleId}")
    public String view(@PathVariable("articleId") Integer articleId, ModelMap mmap)
    {
        Article article = articleService.selectArticleById(articleId);
        if(article.getArticleLabel()!=null && !article.getArticleLabel().equals("")){
            String[] str = article.getArticleLabel().split(",");
            List<String> LabelList = Arrays.asList(str);
            mmap.put("LabelList", LabelList);
        }
        mmap.put("article", article);
        return prefix + "/view";
    }
    /**
     * 查询文章列表
     */
    @RequiresPermissions("business:article:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Article article,ModelMap map)
    {
        startPage();
        article.setStatus("0");
        List<Article> list = articleService.selectArticleList(article);
        for(int i = 0;i<list.size();i++){
            Article article1 = list.get(i);
            article1.setUpdateBy(userService.selectUserById(Long.parseLong(article1.getUpdateBy())).getUserName());
            list.set(i,article1);
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
        List<Article> list = articleService.selectArticleListByNewest();
        return JSON.toJSONString(list);
    }

    /** ---------------------------------    后台管理    ------------------------------------*/

    /**
     * 查询文章管理列表
     */
    @RequiresPermissions("business:article:sysList")
    @PostMapping("/sysList")
    @ResponseBody
    public TableDataInfo sysList(Article article)
    {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }

    /**
     * 导出文章列表
     */
    @RequiresPermissions("business:article:export")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Article article)
    {
        List<Article> list = articleService.selectArticleList(article);
        ExcelUtil<Article> util = new ExcelUtil<Article>(Article.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 新增文章
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("uuid", StringUtils.getUUID());
        return prefix + "/add";
    }

    /**
     * 新增保存文章
     */
    @RequiresPermissions("business:article:add")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Article article)
    {
        article.setCreateBy(getUserId().toString());
        article.setUpdateBy(getUserId().toString());
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改文章
     */
    @GetMapping("/edit/{articleId}")
    public String edit(@PathVariable("articleId") Integer articleId, ModelMap mmap)
    {
        Article article = articleService.selectArticleById(articleId);
        mmap.put("article", article);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章
     */
    @RequiresPermissions("business:article:edit")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Article article)
    {

        article.setUpdateBy(getUserId().toString());
        return toAjax(articleService.updateArticle(article));
    }

    /**
     * 删除文章
     */
    @RequiresPermissions("business:article:remove")
    @Log(title = "文章", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(articleService.deleteArticleByIds(ids));
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
                String fileName = FileUploadUtils.uploadByUuid(RuoYiConfig.getUploadPath(), file,uuid,"");
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
