package com.ruoyi.project.business.atlas.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 图册对象 bl_atlas
 * 
 * @author ys
 * @date 2019-12-24
 */
public class Atlas extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图册ID */
    private Integer atlasId;

    /** 图册标题 */
    @Excel(name = "图册标题")
    private String atlasTitle;

    /** 图册类型 */
    @Excel(name = "图册类型")
    private String atlasType;

    /** 图册标签 */
    @Excel(name = "图册标签")
    private String atlasLabel;

    /** 描述图片 */
    @Excel(name = "描述图片")
    private String atlasImg;

    /** 图册描述 */
    @Excel(name = "图册描述")
    private String atlasDescribe;

    /** 图册内容 */
    @Excel(name = "图册内容")
    private String atlasContent;

    /** 图册文件外链 */
    @Excel(name = "图册文件外链")
    private String atlasLink;

    /** 图册文件外链备注 */
    @Excel(name = "图册文件外链备注")
    private String atlasLinknotes;

    /** 图册状态（0正常 1关闭） */
    @Excel(name = "图册状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 唯一标识uuid */
    private String uuid;

    public void setAtlasId(Integer atlasId) 
    {
        this.atlasId = atlasId;
    }

    public Integer getAtlasId() 
    {
        return atlasId;
    }
    public void setAtlasTitle(String atlasTitle) 
    {
        this.atlasTitle = atlasTitle;
    }

    public String getAtlasTitle() 
    {
        return atlasTitle;
    }
    public void setAtlasType(String atlasType) 
    {
        this.atlasType = atlasType;
    }

    public String getAtlasType() 
    {
        return atlasType;
    }
    public void setAtlasLabel(String atlasLabel) 
    {
        this.atlasLabel = atlasLabel;
    }

    public String getAtlasLabel() 
    {
        return atlasLabel;
    }
    public void setAtlasImg(String atlasImg) 
    {
        this.atlasImg = atlasImg;
    }

    public String getAtlasImg() 
    {
        return atlasImg;
    }
    public void setAtlasDescribe(String atlasDescribe) 
    {
        this.atlasDescribe = atlasDescribe;
    }

    public String getAtlasDescribe() 
    {
        return atlasDescribe;
    }
    public void setAtlasContent(String atlasContent) 
    {
        this.atlasContent = atlasContent;
    }

    public String getAtlasContent() 
    {
        return atlasContent;
    }
    public void setAtlasLink(String atlasLink) 
    {
        this.atlasLink = atlasLink;
    }

    public String getAtlasLink() 
    {
        return atlasLink;
    }
    public void setAtlasLinknotes(String atlasLinknotes) 
    {
        this.atlasLinknotes = atlasLinknotes;
    }

    public String getAtlasLinknotes() 
    {
        return atlasLinknotes;
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
            .append("atlasId", getAtlasId())
            .append("atlasTitle", getAtlasTitle())
            .append("atlasType", getAtlasType())
            .append("atlasLabel", getAtlasLabel())
            .append("atlasImg", getAtlasImg())
            .append("atlasDescribe", getAtlasDescribe())
            .append("atlasContent", getAtlasContent())
            .append("atlasLink", getAtlasLink())
            .append("atlasLinknotes", getAtlasLinknotes())
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
