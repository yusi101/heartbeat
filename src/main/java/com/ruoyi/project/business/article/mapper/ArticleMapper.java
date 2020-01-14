package com.ruoyi.project.business.article.mapper;

import com.ruoyi.project.business.article.domain.Article;
import java.util.List;

/**
 * 文章Mapper接口
 * 
 * @author ys
 * @date 2019-12-24
 */
public interface ArticleMapper 
{
    /**
     * 查询文章
     * 
     * @param articleId 文章ID
     * @return 文章
     */
    public Article selectArticleById(Integer articleId);

    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 删除文章
     * 
     * @param articleId 文章ID
     * @return 结果
     */
    public int deleteArticleById(Integer articleId);

    /**
     * 批量删除文章
     * 
     * @param articleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleByIds(String[] articleIds);

    /**
     * 查询文章列表（精简查询前10条）
     *
     * @return 文章集合
     */
    public List<Article> selectArticleListByNewest();
}
