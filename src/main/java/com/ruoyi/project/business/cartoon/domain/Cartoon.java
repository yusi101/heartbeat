package com.ruoyi.project.business.cartoon.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 漫画对象 bl_cartoon
 * 
 * @author ys
 * @date 2019-12-24
 */
public class Cartoon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 漫画ID */
    private Integer cartoonId;

    /** 漫画标题 */
    @Excel(name = "漫画标题")
    private String cartoonTitle;

    /** 漫画类型 */
    @Excel(name = "漫画类型")
    private String cartoonType;

    /** 漫画标签 */
    @Excel(name = "漫画标签")
    private String cartoonLabel;

    /** 描述图片 */
    @Excel(name = "描述图片")
    private String cartoonImg;

    /** 漫画描述 */
    @Excel(name = "漫画描述")
    private String cartoonDescribe;

    /** 漫画内容 */
    @Excel(name = "漫画内容")
    private String cartoonContent;

    /** 漫画文件外链 */
    @Excel(name = "漫画文件外链")
    private String cartoonLink;

    /** 漫画文件外链备注 */
    @Excel(name = "漫画文件外链备注")
    private String cartoonLinknotes;

    /** 漫画状态（0正常 1关闭） */
    @Excel(name = "漫画状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 唯一标识uuid */
    private String uuid;

    public void setCartoonId(Integer cartoonId) 
    {
        this.cartoonId = cartoonId;
    }

    public Integer getCartoonId() 
    {
        return cartoonId;
    }
    public void setCartoonTitle(String cartoonTitle) 
    {
        this.cartoonTitle = cartoonTitle;
    }

    public String getCartoonTitle() 
    {
        return cartoonTitle;
    }
    public void setCartoonType(String cartoonType) 
    {
        this.cartoonType = cartoonType;
    }

    public String getCartoonType() 
    {
        return cartoonType;
    }
    public void setCartoonLabel(String cartoonLabel) 
    {
        this.cartoonLabel = cartoonLabel;
    }

    public String getCartoonLabel() 
    {
        return cartoonLabel;
    }
    public void setCartoonImg(String cartoonImg) 
    {
        this.cartoonImg = cartoonImg;
    }

    public String getCartoonImg() 
    {
        return cartoonImg;
    }
    public void setCartoonDescribe(String cartoonDescribe) 
    {
        this.cartoonDescribe = cartoonDescribe;
    }

    public String getCartoonDescribe() 
    {
        return cartoonDescribe;
    }
    public void setCartoonContent(String cartoonContent) 
    {
        this.cartoonContent = cartoonContent;
    }

    public String getCartoonContent() 
    {
        return cartoonContent;
    }
    public void setCartoonLink(String cartoonLink) 
    {
        this.cartoonLink = cartoonLink;
    }

    public String getCartoonLink() 
    {
        return cartoonLink;
    }
    public void setCartoonLinknotes(String cartoonLinknotes) 
    {
        this.cartoonLinknotes = cartoonLinknotes;
    }

    public String getCartoonLinknotes() 
    {
        return cartoonLinknotes;
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
            .append("cartoonId", getCartoonId())
            .append("cartoonTitle", getCartoonTitle())
            .append("cartoonType", getCartoonType())
            .append("cartoonLabel", getCartoonLabel())
            .append("cartoonImg", getCartoonImg())
            .append("cartoonDescribe", getCartoonDescribe())
            .append("cartoonContent", getCartoonContent())
            .append("cartoonLink", getCartoonLink())
            .append("cartoonLinknotes", getCartoonLinknotes())
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
