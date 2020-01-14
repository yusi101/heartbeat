package com.ruoyi.project.business.movie.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 电影对象 bl_movie
 * 
 * @author ys
 * @date 2019-12-24
 */
public class Movie extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 电影ID */
    private Integer movieId;

    /** 电影标题 */
    @Excel(name = "电影标题")
    private String movieTitle;

    /** 电影类型 */
    @Excel(name = "电影类型")
    private String movieType;

    /** 电影标签 */
    @Excel(name = "电影标签")
    private String movieLabel;

    /** 描述图片 */
    @Excel(name = "描述图片")
    private String movieImg;

    /** 电影描述 */
    @Excel(name = "电影描述")
    private String movieDescribe;

    /** 电影内容 */
    @Excel(name = "电影内容")
    private String movieContent;

    /** 电影文件外链 */
    @Excel(name = "电影文件外链")
    private String movieLink;

    /** 电影文件外链备注 */
    @Excel(name = "电影文件外链备注")
    private String movieLinknotes;

    /** 电影状态（0正常 1关闭） */
    @Excel(name = "电影状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 唯一标识uuid */
    private String uuid;

    public void setMovieId(Integer movieId) 
    {
        this.movieId = movieId;
    }

    public Integer getMovieId() 
    {
        return movieId;
    }
    public void setMovieTitle(String movieTitle) 
    {
        this.movieTitle = movieTitle;
    }

    public String getMovieTitle() 
    {
        return movieTitle;
    }
    public void setMovieType(String movieType) 
    {
        this.movieType = movieType;
    }

    public String getMovieType() 
    {
        return movieType;
    }
    public void setMovieLabel(String movieLabel) 
    {
        this.movieLabel = movieLabel;
    }

    public String getMovieLabel() 
    {
        return movieLabel;
    }
    public void setMovieImg(String movieImg) 
    {
        this.movieImg = movieImg;
    }

    public String getMovieImg() 
    {
        return movieImg;
    }
    public void setMovieDescribe(String movieDescribe) 
    {
        this.movieDescribe = movieDescribe;
    }

    public String getMovieDescribe() 
    {
        return movieDescribe;
    }
    public void setMovieContent(String movieContent) 
    {
        this.movieContent = movieContent;
    }

    public String getMovieContent() 
    {
        return movieContent;
    }
    public void setMovieLink(String movieLink) 
    {
        this.movieLink = movieLink;
    }

    public String getMovieLink() 
    {
        return movieLink;
    }
    public void setMovieLinknotes(String movieLinknotes) 
    {
        this.movieLinknotes = movieLinknotes;
    }

    public String getMovieLinknotes() 
    {
        return movieLinknotes;
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
            .append("movieId", getMovieId())
            .append("movieTitle", getMovieTitle())
            .append("movieType", getMovieType())
            .append("movieLabel", getMovieLabel())
            .append("movieImg", getMovieImg())
            .append("movieDescribe", getMovieDescribe())
            .append("movieContent", getMovieContent())
            .append("movieLink", getMovieLink())
            .append("movieLinknotes", getMovieLinknotes())
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
