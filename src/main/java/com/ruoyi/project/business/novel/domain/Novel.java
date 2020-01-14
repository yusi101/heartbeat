package com.ruoyi.project.business.novel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 小说对象 bl_novel
 * 
 * @author ys
 * @date 2019-12-24
 */
public class Novel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 小说ID */
    private Integer novelId;

    /** 小说标题 */
    @Excel(name = "小说标题")
    private String novelTitle;

    /** 小说类型 */
    @Excel(name = "小说类型")
    private String novelType;

    /** 小说标签 */
    @Excel(name = "小说标签")
    private String novelLabel;

    /** 描述图片 */
    @Excel(name = "描述图片")
    private String novelImg;

    /** 小说描述 */
    @Excel(name = "小说描述")
    private String novelDescribe;

    /** 小说内容 */
    @Excel(name = "小说内容")
    private String novelContent;

    /** 小说文件外链 */
    @Excel(name = "小说文件外链")
    private String novelLink;

    /** 小说文件外链备注 */
    @Excel(name = "小说文件外链备注")
    private String novelLinknotes;

    /** 小说状态（0正常 1关闭） */
    @Excel(name = "小说状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 唯一标识uuid */
    private String uuid;

    public void setNovelId(Integer novelId) 
    {
        this.novelId = novelId;
    }

    public Integer getNovelId() 
    {
        return novelId;
    }
    public void setNovelTitle(String novelTitle) 
    {
        this.novelTitle = novelTitle;
    }

    public String getNovelTitle() 
    {
        return novelTitle;
    }
    public void setNovelType(String novelType) 
    {
        this.novelType = novelType;
    }

    public String getNovelType() 
    {
        return novelType;
    }
    public void setNovelLabel(String novelLabel) 
    {
        this.novelLabel = novelLabel;
    }

    public String getNovelLabel() 
    {
        return novelLabel;
    }
    public void setNovelImg(String novelImg) 
    {
        this.novelImg = novelImg;
    }

    public String getNovelImg() 
    {
        return novelImg;
    }
    public void setNovelDescribe(String novelDescribe) 
    {
        this.novelDescribe = novelDescribe;
    }

    public String getNovelDescribe() 
    {
        return novelDescribe;
    }
    public void setNovelContent(String novelContent) 
    {
        this.novelContent = novelContent;
    }

    public String getNovelContent() 
    {
        return novelContent;
    }
    public void setNovelLink(String novelLink) 
    {
        this.novelLink = novelLink;
    }

    public String getNovelLink() 
    {
        return novelLink;
    }
    public void setNovelLinknotes(String novelLinknotes) 
    {
        this.novelLinknotes = novelLinknotes;
    }

    public String getNovelLinknotes() 
    {
        return novelLinknotes;
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
            .append("novelId", getNovelId())
            .append("novelTitle", getNovelTitle())
            .append("novelType", getNovelType())
            .append("novelLabel", getNovelLabel())
            .append("novelImg", getNovelImg())
            .append("novelDescribe", getNovelDescribe())
            .append("novelContent", getNovelContent())
            .append("novelLink", getNovelLink())
            .append("novelLinknotes", getNovelLinknotes())
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
