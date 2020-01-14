package com.ruoyi.project.business.article.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 文章对象 bl_article
 * 
 * @author ys
 * @date 2019-12-24
 */
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章ID */
    private Integer articleId;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String articleTitle;

    /** 文章类型 */
    @Excel(name = "文章类型")
    private String articleType;

    /** 文章标签 */
    @Excel(name = "文章标签")
    private String articleLabel;

    /** 描述图片 */
    @Excel(name = "描述图片")
    private String articleImg;

    /** 文章描述 */
    @Excel(name = "文章描述")
    private String articleDescribe;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String articleContent;

    /** 文章文件外链 */
    @Excel(name = "文章文件外链")
    private String articleLink;

    /** 文章文件外链备注 */
    @Excel(name = "文章文件外链备注")
    private String articleLinknotes;

    /** 文章状态（0正常 1关闭） */
    @Excel(name = "文章状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 唯一标识uuid */
    private String uuid;

    public void setArticleId(Integer articleId) 
    {
        this.articleId = articleId;
    }

    public Integer getArticleId() 
    {
        return articleId;
    }
    public void setArticleTitle(String articleTitle) 
    {
        this.articleTitle = articleTitle;
    }

    public String getArticleTitle() 
    {
        return articleTitle;
    }
    public void setArticleType(String articleType) 
    {
        this.articleType = articleType;
    }

    public String getArticleType() 
    {
        return articleType;
    }
    public void setArticleLabel(String articleLabel) 
    {
        this.articleLabel = articleLabel;
    }

    public String getArticleLabel() 
    {
        return articleLabel;
    }
    public void setArticleImg(String articleImg) 
    {
        this.articleImg = articleImg;
    }

    public String getArticleImg() 
    {
        return articleImg;
    }
    public void setArticleDescribe(String articleDescribe) 
    {
        this.articleDescribe = articleDescribe;
    }

    public String getArticleDescribe() 
    {
        return articleDescribe;
    }
    public void setArticleContent(String articleContent) 
    {
        this.articleContent = articleContent;
    }

    public String getArticleContent() 
    {
        return articleContent;
    }
    public void setArticleLink(String articleLink) 
    {
        this.articleLink = articleLink;
    }

    public String getArticleLink() 
    {
        return articleLink;
    }
    public void setArticleLinknotes(String articleLinknotes) 
    {
        this.articleLinknotes = articleLinknotes;
    }

    public String getArticleLinknotes() 
    {
        return articleLinknotes;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUuid(String uuid) 
    {
        this.uuid = uuid;
    }

    public String getUuid() 
    {
        return uuid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("articleId", getArticleId())
            .append("articleTitle", getArticleTitle())
            .append("articleType", getArticleType())
            .append("articleLabel", getArticleLabel())
            .append("articleImg", getArticleImg())
            .append("articleDescribe", getArticleDescribe())
            .append("articleContent", getArticleContent())
            .append("articleLink", getArticleLink())
            .append("articleLinknotes", getArticleLinknotes())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("uuid", getUuid())
            .toString();
    }
}
