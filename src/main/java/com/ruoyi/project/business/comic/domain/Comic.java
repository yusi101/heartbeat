package com.ruoyi.project.business.comic.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 动漫对象 bl_comic
 * 
 * @author ys
 * @date 2019-12-24
 */
public class Comic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 动漫ID */
    private Integer comicId;

    /** 动漫标题 */
    @Excel(name = "动漫标题")
    private String comicTitle;

    /** 动漫类型 */
    @Excel(name = "动漫类型")
    private String comicType;

    /** 动漫标签 */
    @Excel(name = "动漫标签")
    private String comicLabel;

    /** 描述图片 */
    @Excel(name = "描述图片")
    private String comicImg;

    /** 动漫描述 */
    @Excel(name = "动漫描述")
    private String comicDescribe;

    /** 动漫内容 */
    @Excel(name = "动漫内容")
    private String comicContent;

    /** 动漫文件外链 */
    @Excel(name = "动漫文件外链")
    private String comicLink;

    /** 动漫文件外链备注 */
    @Excel(name = "动漫文件外链备注")
    private String comicLinknotes;

    /** 动漫状态（0正常 1关闭） */
    @Excel(name = "动漫状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 唯一标识uuid */
    private String uuid;

    public void setComicId(Integer comicId) 
    {
        this.comicId = comicId;
    }

    public Integer getComicId() 
    {
        return comicId;
    }
    public void setComicTitle(String comicTitle) 
    {
        this.comicTitle = comicTitle;
    }

    public String getComicTitle() 
    {
        return comicTitle;
    }
    public void setComicType(String comicType) 
    {
        this.comicType = comicType;
    }

    public String getComicType() 
    {
        return comicType;
    }
    public void setComicLabel(String comicLabel) 
    {
        this.comicLabel = comicLabel;
    }

    public String getComicLabel() 
    {
        return comicLabel;
    }
    public void setComicImg(String comicImg) 
    {
        this.comicImg = comicImg;
    }

    public String getComicImg() 
    {
        return comicImg;
    }
    public void setComicDescribe(String comicDescribe) 
    {
        this.comicDescribe = comicDescribe;
    }

    public String getComicDescribe() 
    {
        return comicDescribe;
    }
    public void setComicContent(String comicContent) 
    {
        this.comicContent = comicContent;
    }

    public String getComicContent() 
    {
        return comicContent;
    }
    public void setComicLink(String comicLink) 
    {
        this.comicLink = comicLink;
    }

    public String getComicLink() 
    {
        return comicLink;
    }
    public void setComicLinknotes(String comicLinknotes) 
    {
        this.comicLinknotes = comicLinknotes;
    }

    public String getComicLinknotes() 
    {
        return comicLinknotes;
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
            .append("comicId", getComicId())
            .append("comicTitle", getComicTitle())
            .append("comicType", getComicType())
            .append("comicLabel", getComicLabel())
            .append("comicImg", getComicImg())
            .append("comicDescribe", getComicDescribe())
            .append("comicContent", getComicContent())
            .append("comicLink", getComicLink())
            .append("comicLinknotes", getComicLinknotes())
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
