package com.ruoyi.project.business.article.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.article.mapper.ArticleMapper;
import com.ruoyi.project.business.article.domain.Article;
import com.ruoyi.project.business.article.service.IArticleService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 文章Service业务层处理
 * 
 * @author ys
 * @date 2019-12-24
 */
@Service
public class ArticleServiceImpl implements IArticleService
{
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 查询文章
     * 
     * @param articleId 文章ID
     * @return 文章
     */
    @Override
    public Article selectArticleById(Integer articleId)
    {
        return articleMapper.selectArticleById(articleId);
    }

    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章
     */
    @Override
    public List<Article> selectArticleList(Article article)
    {
        return articleMapper.selectArticleList(article);
    }

    /**
     * 根据 标题和标签 模糊查询
     * @param keyword
     * @return
     */
    @Override
    public List<Article> selectArticleListByKw(String keyword)
    {
        return articleMapper.selectArticleListByKw(keyword);
    }

    /**
     * 新增文章
     * 
     * @param article 文章
     * @return 结果
     */
    @Override
    public int insertArticle(Article article)
    {
        article.setCreateBy(ShiroUtils.getUserId().toString());
        article.setCreateTime(DateUtils.getNowDate());
        return articleMapper.insertArticle(article);
    }

    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    @Override
    public int updateArticle(Article article)
    {

        article.setUpdateBy(ShiroUtils.getUserId().toString());
        article.setUpdateTime(DateUtils.getNowDate());
        return articleMapper.updateArticle(article);
    }

    /**
     * 删除文章对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteArticleByIds(String ids)
    {
        return articleMapper.deleteArticleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章信息
     * 
     * @param articleId 文章ID
     * @return 结果
     */
    @Override
    public int deleteArticleById(Integer articleId)
    {
        return articleMapper.deleteArticleById(articleId);
    }

    /**
     * 查询文章列表（精简查询前10条）
     *
     * @return 结果
     */
    @Override
    public List<Article> selectArticleListByNewest() {
        return articleMapper.selectArticleListByNewest();
    }
}
